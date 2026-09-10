// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.supurdueper.robotOffseason.subsystems;

import org.supurdueper.lib.subsystems.VelocitySubsystem;
import org.supurdueper.robotOffseason.CanId;
import org.supurdueper.robotOffseason.Constants;
import org.supurdueper.robotOffseason.state.RobotStates;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;

import static edu.wpi.first.units.Units.*;

import org.supurdueper.lib.subsystems.SupurdueperSubsystem;
import org.supurdueper.lib.subsystems.TalonFXSubsystem;

import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;

public class intake extends VelocitySubsystem implements SupurdueperSubsystem{
  /** Creates a new Intake. */
  public intake() {}

  @Override
  public void periodic() {
    super.periodic();
  }

  @Override
  public Slot0Configs pidGains() {
    return new Slot0Configs()
      .withKP(Constants.IntakeConstants.kP)
      .withKI(Constants.IntakeConstants.kI)
      .withKS(Constants.IntakeConstants.kS)
      .withKV(Constants.IntakeConstants.kV)
      .withKA(Constants.IntakeConstants.kA);
  }

  @Override
  public AngularVelocity velocityTolerance() {
    return Constants.IntakeConstants.kTolerance;
  }

  @Override
  public SysIdRoutine sysIdConfig() {
    return null;
  }

  @Override
  public CanId canIdLeader() {
    return CanId.INTAKE_ONE;
  }

  @Override
  public CanId canIdFollower() {
    return CanId.INTAKE_TWO;
  }

  @Override
  public boolean followerInverted() {
    return false;
  }

  @Override
  public CurrentLimitsConfigs currentLimits() {
    return Constants.IntakeConstants.kCurrentLimits;
  }

  @Override
  public boolean inverted() {
    return false;
  }

  @Override
  public boolean brakeMode() {
    return false;
  }

  public void runFoward() {
    setVelocity(Constants.IntakeConstants.kFowardVelocity);
  }

  public void runBrake() {
    setVelocity(RotationsPerSecond.of(0));
  }

  public Command runintake() {
    return Commands.runEnd(this::runintake ,this::runBrake);
  }

  @Override
  public void bindCommands() {
  RobotStates.actionIntake.whileTrue(runintake());
  }
}
