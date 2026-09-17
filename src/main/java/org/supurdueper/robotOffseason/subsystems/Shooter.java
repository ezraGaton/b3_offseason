// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.supurdueper.robotOffseason.subsystems;

import org.supurdueper.lib.subsystems.SupurdueperSubsystem;
import org.supurdueper.lib.subsystems.VelocitySubsystem;
import org.supurdueper.robotOffseason.CanId;

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
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'pidGains'");
  }

  @Override
  public AngularVelocity velocityTolerance() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'velocityTolerance'");
  }

  @Override
  public SysIdRoutine sysIdConfig() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'sysIdConfig'");
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
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'currentLimits'");
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
