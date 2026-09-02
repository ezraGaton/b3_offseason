// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.supurdueper.robotOffseason.subsystems;

import org.supurdueper.lib.subsystems.VelocitySubsystem;
import org.supurdueper.robotOffseason.CanId;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;

import org.supurdueper.lib.subsystems.SupurdueperSubsystem;
import org.supurdueper.lib.subsystems.TalonFXSubsystem;

import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;

public class Shooter extends VelocitySubsystem implements SupurdueperSubsystem{
  /** Creates a new Shooter. */
  public Shooter() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
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
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'canIdLeader'");
  }

  @Override
  public CanId canIdFollower() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'canIdFollower'");
  }

  @Override
  public boolean followerInverted() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'followerInverted'");
  }

  @Override
  public CurrentLimitsConfigs currentLimits() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'currentLimits'");
  }

  @Override
  public boolean inverted() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'inverted'");
  }

  @Override
  public boolean brakeMode() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'brakeMode'");
  }

  @Override
  public void bindCommands() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'bindCommands'");
  }
}
