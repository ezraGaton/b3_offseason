// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.supurdueper.robotOffseason.subsystems;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.RPM;
import static edu.wpi.first.units.Units.Volts;

import org.supurdueper.lib.Alert;
import org.supurdueper.lib.LoggedTunableNumber;
import org.supurdueper.lib.TalonFXFactory;
import org.supurdueper.lib.subsystems.SupurdueperSubsystem;
import org.supurdueper.lib.subsystems.VelocitySubsystem;
import org.supurdueper.robotOffseason.CanId;
import org.supurdueper.robotOffseason.Constants;
import org.supurdueper.robotOffseason.Constants.LookupTables;
import org.supurdueper.robotOffseason.Constants.ShooterConstants;
import org.supurdueper.robotOffseason.Robot;
import org.supurdueper.robotOffseason.RobotContainer;
import org.supurdueper.robotOffseason.state.RobotStates;
import org.supurdueper.robotOffseason.utils.FieldCalculations;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;

import dev.doglog.DogLog;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;

public class Shooter extends VelocitySubsystem implements SupurdueperSubsystem {

  private final LoggedTunableNumber shooterVelocity;

  /** Creates a new Shooter. */
  public Shooter() {
    config.Feedback.SensorToMechanismRatio = ShooterConstants.shooterGearRatio;
    config.TorqueCurrent.PeakForwardTorqueCurrent = ShooterConstants.kMaxAmps;
    config.TorqueCurrent.PeakReverseTorqueCurrent = 0;
    config.MotorOutput.PeakForwardDutyCycle = 1.0;
    config.MotorOutput.PeakForwardDutyCycle = 0.0;
    configureMotors();

    //Creates followers because we have more than
    TalonFXFactory.createPermanentFollowerTalon(CanId.SHOOTER_TWO, motor, false);
    TalonFXFactory.createPermanentFollowerTalon(CanId.SHOOTER_THREE, motor, true);
    TalonFXFactory.createPermanentFollowerTalon(CanId.SHOOTER_FOUR, motor, true);
    Robot.add(this);
    shooterVelocity = new LoggedTunableNumber("Shot Tuning/Speed (RPM)");
    shooterVelocity.initDefault(1500);

  }


  protected boolean isAtVelocity() {
    if (getVelocity().lt(RPM.of(1600))) return false;
    return super.isAtVelocity();
  }

  @Override
  public void periodic() {
    super.periodic();
    DogLog.log("Shooter/Current RPM", getVelocity().in(RPM));
    DogLog.log("Shooter/Target RPM", getSetpoint().in(RPM));
    DogLog.log("Shooter/At Velocity", isAtVelocity());
    DogLog.log("Shooter/Current", motor.getTorqueCurrent().getValue().in(Amps));
    DogLog.log("MotorRPM", motor.getRotorVelocity().getValue().in(RPM));
 
  }

  private AngularVelocity getShotVelocity() {
    if (Constants.tuningMode) {
        return RPM.of(shooterVelocity.get());
    } else if (RobotStates.actionSetShot.getAsBoolean()){
            return Constants.ShooterConstants.kShootRPM;
    } else {
        double distanceToGoalMeters = FieldCalculations.distanceToGoal(
                        RobotContainer.getDrivetrain().getState().Pose)
                .in(Meters);
        return RPM.of(LookupTables.distanceToShooterRPM.get(distanceToGoalMeters));
    }
  }

  @Override
  public void bindCommands() {
    RobotStates.actionAim.or(RobotStates.actionShoot).onTrue(goToVelocity(this::getShotVelocity));
    RobotStates.auto_aim.or(RobotStates.auto_shoot).onTrue(goToVelocity(this::getShotVelocity));
    RobotStates.actionAim.or(RobotStates.actionShoot).or(RobotStates.actionSetShot).onFalse(goToVelocity(() -> ShooterConstants.kIdleRPM));
    RobotStates.auto_rev.onTrue(goToVelocity(() -> ShooterConstants.kRevRpm));
    RobotStates.actionSetShot.whileTrue(goToVelocity(() -> ShooterConstants.kShootRPM));
  }

  @Override
  public Slot0Configs pidGains() {
    return new Slot0Configs()
    .withKP(Constants.ShooterConstants.kP)
    .withKI(Constants.ShooterConstants.kI)
    .withKS(Constants.ShooterConstants.kS)
    .withKV(Constants.ShooterConstants.kV)
    .withKA(Constants.ShooterConstants.kA);
  }

  @Override
  public AngularVelocity velocityTolerance() {
    return Constants.ShooterConstants.kTolerance;
  }

  @Override
  public SysIdRoutine sysIdConfig() {
    return null;
  }

  @Override
  public CanId canIdLeader() {
    return CanId.SHOOTER_ONE;
  }

  @Override
  public CanId canIdFollower() {
    return null;
  }

  @Override
  public boolean followerInverted() {
    return false;
  }

  @Override
  public CurrentLimitsConfigs currentLimits() {
    return Constants.ShooterConstants.kCurrentLimits;
  }

  @Override
  public boolean inverted() {
    return false;
  }

  @Override
  public boolean brakeMode() {
    return false;
  }
}
