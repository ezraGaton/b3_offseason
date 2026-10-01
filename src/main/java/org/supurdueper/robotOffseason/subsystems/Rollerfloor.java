// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.supurdueper.robotOffseason.subsystems;

import static edu.wpi.first.units.Units.RotationsPerSecond;
import static edu.wpi.first.units.Units.Volt;

import org.supurdueper.lib.subsystems.SupurdueperSubsystem;
import org.supurdueper.lib.subsystems.VelocitySubsystem;
import org.supurdueper.robotOffseason.CanId;
import org.supurdueper.robotOffseason.Constants;
import org.supurdueper.robotOffseason.Constants.IntakeConstants;
import org.supurdueper.robotOffseason.Constants.RollerFloorConstants;
import org.supurdueper.robotOffseason.Robot;
import org.supurdueper.robotOffseason.state.RobotStates;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;

import edu.wpi.first.units.AngularVelocityUnit;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.units.VoltageUnit;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;

public class Rollerfloor extends VelocitySubsystem implements SupurdueperSubsystem {
  /** Creates a new RollerFloor. */
  public Rollerfloor() {
    config = config.withFeedback(new FeedbackConfigs().withSensorToMechanismRatio(RollerFloorConstants.kGearRatio));
    configureMotors();
    Robot.add(this);
  }

  @Override
  public void periodic() {
    super.periodic();
  }

  @Override
  public void bindCommands() {
    RobotStates.actionIntake.whileTrue(runIntake());
    RobotStates.actionShoot.whileTrue(runShoot());
  }

  public void runSlow(){
    //runVoltage(Volt.of(12));
    setVelocity(Constants.RollerFloorConstants.kSlowSpeed);
  }

  public void runFast(){
    setVelocity(Constants.RollerFloorConstants.kFastSpeed);
  }

  public void runStop(){
    setVelocity(0);
  }

  public Command runIntake(){
    return Commands.runEnd(this::runSlow,this::runStop);
  }
  public Command runShoot(){
    return Commands.runEnd(this::runFast,this::runStop);
  }
  @Override
  public Slot0Configs pidGains() {
     return new Slot0Configs()
      .withKP(Constants.RollerFloorConstants.kP)
      .withKI(Constants.RollerFloorConstants.kI)
      .withKS(Constants.RollerFloorConstants.kS)
      .withKV(Constants.RollerFloorConstants.kV)
      .withKA(Constants.RollerFloorConstants.kA);
  }

  @Override
  public AngularVelocity velocityTolerance() {
    return RotationsPerSecond.of(Constants.RollerFloorConstants.kTolerance);
  }

  @Override
  public SysIdRoutine sysIdConfig() {
   return null;
  }

  @Override
  public CanId canIdLeader() {
   return CanId.ROLLER_FLOOR_ONE;
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
    return Constants.RollerFloorConstants.kCurrentLimits;
  }

  @Override
  public boolean inverted() {
   return true;
  }

  @Override
  public boolean brakeMode() {
   return false;
  }
}
