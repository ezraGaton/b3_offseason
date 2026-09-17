// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.supurdueper.robotOffseason.subsystems;

import org.supurdueper.lib.Alert;
import org.supurdueper.lib.subsystems.SupurdueperSubsystem;
import org.supurdueper.lib.subsystems.VelocitySubsystem;
import org.supurdueper.robotOffseason.CanId;
import org.supurdueper.robotOffseason.Constants;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;

import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;

public class Shooter extends VelocitySubsystem implements SupurdueperSubsystem {
  /** Creates a new Shooter. */
  public Shooter() {}

  @Override
  public void periodic() {
    super.periodic();
  }

  @Override
  public void bindCommands() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'bindCommands'");
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
    return CanId.SHOOTER_TWO;
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
