// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.supurdueper.robotOffseason.subsystems;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.Volts;

import org.supurdueper.lib.LoggedTunableNumber;
import org.supurdueper.lib.subsystems.PositionSubsystem;
import org.supurdueper.lib.subsystems.SupurdueperSubsystem;
import org.supurdueper.robotOffseason.CanId;
import org.supurdueper.robotOffseason.Constants;
import org.supurdueper.robotOffseason.Constants.LookupTables;
import org.supurdueper.robotOffseason.Constants.ShooterHoodConstants;
import org.supurdueper.robotOffseason.Robot;
import org.supurdueper.robotOffseason.RobotContainer;
import org.supurdueper.robotOffseason.state.RobotStates;
import org.supurdueper.robotOffseason.utils.FieldCalculations;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.SoftwareLimitSwitchConfigs;
import com.ctre.phoenix6.configs.VoltageConfigs;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.signals.GravityTypeValue;

import dev.doglog.DogLog;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;


public class ShooterHood extends PositionSubsystem implements SupurdueperSubsystem {
  
  private PositionVoltage noMagicMotion = new PositionVoltage(0);
  private final LoggedTunableNumber shooterAngle;
  /** Creates a new ShooterHood. */
  public ShooterHood() {
      config = config.withFeedback(new FeedbackConfigs().withSensorToMechanismRatio(ShooterHoodConstants.gearRatio))
                .withVoltage(new VoltageConfigs()
                        .withPeakForwardVoltage(ShooterHoodConstants.kPeakForwardVoltage)
                        .withPeakReverseVoltage(ShooterHoodConstants.kPeakReverseVoltage));
        configureMotors();
        Robot.add(this);
        motor.setPosition(ShooterHoodConstants.kZeroPosition);
        shooterAngle = new LoggedTunableNumber("Shot Tuning/Angle (Deg)");
        shooterAngle.initDefault(22);
  }

  @Override
  protected void setPosition(Angle position){
    motor.setControl(noMagicMotion.withPosition(position));
  }

  @Override
  protected void setPosition(double position){
    motor.setControl(noMagicMotion.withPosition(position));
  }

  public Command zero() {
    return run(() -> motor.setControl(voltageRequest.withOutput(-3).withIgnoreSoftwareLimits(true)))
            .until(() -> motor.getStatorCurrent().getValueAsDouble() > 40.0)
            .andThen(runOnce(
                    () -> motor.setControl(voltageRequest.withOutput(0).withIgnoreSoftwareLimits(false))))
            .andThen(runOnce(() -> motor.setPosition(ShooterHoodConstants.kZeroPosition)));
    }

  @Override
  public void periodic() {
    super.periodic();
    DogLog.log("ShooterHood/Position (Deg)", getPosition().in(Degrees));
    DogLog.log("ShooterHood/Target Position (Deg)", getSetpoint().in(Degrees));
    DogLog.log("ShooterHood/Current", motor.getTorqueCurrent().getValue().in(Amps));
      if (Constants.tuningMode) {
          SmartDashboard.putNumber(
                    "Tuning/Shot Tuning/Distance",
                    FieldCalculations.distanceToGoal(
                                    RobotContainer.getDrivetrain().getState().Pose)
                            .in(Meters));
        }
  }
    
  public Angle getShotAngle() {
        if (Constants.tuningMode) {
            return Degrees.of(shooterAngle.get());
        } else if (RobotStates.actionSetShot.getAsBoolean()){
            return Constants.ShooterHoodConstants.kSetShotAngle;
        } else {
            double distanceToGoalMeters = FieldCalculations.distanceToGoal(
                            RobotContainer.getDrivetrain().getState().Pose)
                    .in(Meters);
            return Degrees.of(LookupTables.distanceToShooterAngle.get(distanceToGoalMeters));
        }
    }

  @Override
  public Slot0Configs pidGains() {
    return new Slot0Configs()
    .withGravityType(GravityTypeValue.Arm_Cosine)
    .withKP(Constants.ShooterHoodConstants.kP)
    .withKA(Constants.ShooterHoodConstants.kA)
    .withKG(Constants.ShooterHoodConstants.kG)
    .withKS(Constants.ShooterHoodConstants.kS)
    .withKV(Constants.ShooterHoodConstants.kV);
  }

  @Override
  public MotionMagicConfigs motionMagicConfig() {
    return new MotionMagicConfigs()
                .withMotionMagicExpo_kV(0)
                .withMotionMagicExpo_kA(0)
                .withMotionMagicCruiseVelocity(0)
                .withMotionMagicAcceleration(0);
  }

  @Override
  public SoftwareLimitSwitchConfigs softLimitConfig() {
      return new SoftwareLimitSwitchConfigs()
    .withForwardSoftLimitThreshold(Constants.ShooterHoodConstants.kFowardSoftLimit)
    .withForwardSoftLimitEnable(true)
    .withReverseSoftLimitThreshold(Constants.ShooterHoodConstants.kBackwardSoftLimit)
    .withReverseSoftLimitEnable(true);
  }

  @Override
  public Angle positionTolerance() {
    return Constants.ShooterHoodConstants.positionTolerance;
  }

  @Override
  public SysIdRoutine sysIdConfig() {
    return null;
  }

  @Override
  public CanId canIdLeader() {
    return CanId.SHOOTER_PIVOT_ONE;
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
    return Constants.ShooterHoodConstants.kCurrentLimits;
  }

  @Override
  public boolean inverted() {
    return false;
  }

  @Override
  public boolean brakeMode() {
    return true;
    
  }


  @Override
  public void bindCommands() {
        RobotStates.teleop.onTrue(goToPosition(() -> Constants.ShooterHoodConstants.kBackwardSoftLimit));
        RobotStates.actionAim
                .or(RobotStates.actionShoot)
                .or(RobotStates.auto_aim)
                .or(RobotStates.auto_shoot)
                .whileTrue(goToPosition(this::getShotAngle));
        RobotStates.actionSetShot.onTrue(goToPosition(() -> Constants.ShooterHoodConstants.kSetShotAngle));
        RobotStates.auto_drop_hood.onTrue(goToPosition(() -> Constants.ShooterHoodConstants.kBackwardSoftLimit));
        RobotStates.actionAim
                .or(RobotStates.actionShoot)
                .or(RobotStates.auto_aim)
                .or(RobotStates.auto_shoot)
                .or(RobotStates.actionSetShot)
                .onFalse(goToPosition(() -> (Constants.ShooterHoodConstants.kBackwardSoftLimit)));
        RobotStates.driver.downDpad.onTrue(zero());
        RobotStates.testController.leftStickY.whileTrue(
                runEnd(() -> runVoltage(Volts.of(6 * RobotStates.testController.getDriveFwdPositive())), this::stop));


  }
}
