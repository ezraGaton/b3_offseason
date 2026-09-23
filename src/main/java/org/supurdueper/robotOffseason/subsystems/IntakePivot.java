// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.supurdueper.robotOffseason.subsystems;

import org.supurdueper.lib.subsystems.PositionSubsystem;
import org.supurdueper.lib.subsystems.SupurdueperSubsystem;
import org.supurdueper.robotOffseason.CanId;
import org.supurdueper.robotOffseason.Constants;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.SoftwareLimitSwitchConfigs;
import com.ctre.phoenix6.signals.GravityTypeValue;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;

public class IntakePivot extends PositionSubsystem implements SupurdueperSubsystem {
  /** Creates a new IntakePivot. */
  public IntakePivot() {}

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
  protected void setPosition(Angle position){
    // TODO
  }


  @Override
  public Slot0Configs pidGains() {
    return new Slot0Configs()
    .withGravityType(GravityTypeValue.Arm_Cosine)
    .withKP(Constants.IntakePivotConstants.kP)
    .withKI(Constants.IntakePivotConstants.kI)
    .withKS(Constants.IntakePivotConstants.kS)
    .withKV(Constants.IntakePivotConstants.kV)
    .withKA(Constants.IntakePivotConstants.kA)
    .withKG(Constants.IntakePivotConstants.kG);
  }

  @Override
  public MotionMagicConfigs motionMagicConfig() {
    return null;
  }

  @Override
  public SoftwareLimitSwitchConfigs softLimitConfig() {
    return new SoftwareLimitSwitchConfigs()
    .withForwardSoftLimitThreshold(Constants.IntakePivotConstants.kFowardSoftLimit)
    .withForwardSoftLimitEnable(true)
    .withReverseSoftLimitThreshold(Constants.IntakePivotConstants.kBackwardSoftLimit)
    .withReverseSoftLimitEnable(true);
  }

  @Override
  public Angle positionTolerance() {
    return Constants.IntakePivotConstants.positionTolerance;
  }

  @Override
  public SysIdRoutine sysIdConfig() {
    return null;
  }

  @Override
  public CanId canIdLeader() {
    return CanId.INTAKE_PIVOT_ONE;
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
    return Constants.IntakePivotConstants.kCurrentLimits;
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
