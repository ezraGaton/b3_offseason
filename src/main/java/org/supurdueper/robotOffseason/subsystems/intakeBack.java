// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.supurdueper.robotOffseason.subsystems;

import org.supurdueper.lib.subsystems.VelocitySubsystem;
import org.supurdueper.robotOffseason.CanId;
import org.supurdueper.robotOffseason.Constants;
import org.supurdueper.robotOffseason.Constants.IntakeBackConstants;
import org.supurdueper.robotOffseason.Robot;
import org.supurdueper.robotOffseason.state.RobotStates;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;

import dev.doglog.DogLog;

import static edu.wpi.first.units.Units.*;

import org.supurdueper.lib.subsystems.SupurdueperSubsystem;
import org.supurdueper.lib.subsystems.TalonFXSubsystem;

import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;

public class intakeBack extends VelocitySubsystem implements SupurdueperSubsystem{
  /** Creates a new Intake. */
  public intakeBack() {
    config = config.withFeedback(new FeedbackConfigs().withSensorToMechanismRatio(IntakeBackConstants.kGearRatio));
    configureMotors();
    Robot.add(this);
  }
  @Override
  public void periodic() {
    super.periodic();
    DogLog.log("IntakeBack/target rps", getSetpoint().in(RotationsPerSecond));
    DogLog.log("IntakeBack/Current RPS", getVelocity().in(RotationsPerSecond));
  }

  @Override
  public Slot0Configs pidGains() {
    return new Slot0Configs()
      .withKP(Constants.IntakeBackConstants.kP)
      .withKI(Constants.IntakeBackConstants.kI)
      .withKS(Constants.IntakeBackConstants.kS)
      .withKV(Constants.IntakeBackConstants.kV)
      .withKA(Constants.IntakeBackConstants.kA);
  }

  @Override
  public AngularVelocity velocityTolerance() {
    return Constants.IntakeBackConstants.kTolerance;
  }

  @Override
  public SysIdRoutine sysIdConfig() {
    return null;
  }

  @Override
  public CanId canIdLeader() {
    return CanId.INTAKE_TWO;
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
    return Constants.IntakeBackConstants.kCurrentLimits;
  }

  @Override
  public boolean inverted() {
    return true;
  }

  @Override
  public boolean brakeMode() {
    return false;
  }


  @Override
  public void bindCommands() {
  //RobotStates.testController.B.onTrue(goToVelocity(() -> RotationsPerSecond.of(10)));
  //RobotStates.testController.X.onTrue(goToVelocity(() -> RotationsPerSecond.of(20)));
  //RobotStates.testController.Y.onTrue(goToVelocity(() -> RotationsPerSecond.of(40)));
  //RobotStates.testController.A.onTrue(run(this::stop));
  RobotStates.actionIntake.onTrue(goToVelocity(()-> RotationsPerSecond.of(40)));
  RobotStates.actionIntake.onFalse(goToVelocity(()-> RotationsPerSecond.of(0)));

  }
}
