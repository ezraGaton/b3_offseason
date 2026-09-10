// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.supurdueper.robotOffseason.subsystems;

import static edu.wpi.first.units.Units.RotationsPerSecond;

import java.rmi.server.RemoteStub;

import org.supurdueper.lib.subsystems.SupurdueperSubsystem;
import org.supurdueper.lib.subsystems.VelocitySubsystem;
import org.supurdueper.robotOffseason.CanId;
import org.supurdueper.robotOffseason.Constants;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;

import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;

public class Indexer extends VelocitySubsystem implements SupurdueperSubsystem {
  /** Creates a new Indexer. */
  public Indexer() {}

  @Override
  public void periodic() {
    super.periodic();
  }

  @Override
  public Slot0Configs pidGains() {
    return new Slot0Configs()
    .withKP(Constants.IndexerConstants.kP)
    .withKS(Constants.IndexerConstants.kS)
    .withKV(Constants.IndexerConstants.kV)
    .withKI(Constants.IndexerConstants.kI)
    .withKA(Constants.IndexerConstants.kA);
  }

  @Override
  public AngularVelocity velocityTolerance() {
    return Constants.IndexerConstants.kTolerance;
  }

  @Override
  public SysIdRoutine sysIdConfig() {
    return null;
  }

  @Override
  public CanId canIdLeader() {
    return CanId.INDEXER_ONE;
  }

  @Override
  public CurrentLimitsConfigs currentLimits() {
    return Constants.IndexerConstants.kCurrentLimitsIndexer;
  }

  @Override
  public boolean inverted() {
    return false;
  }

  @Override
  public boolean brakeMode() {
    return false;
  }



  @Override
  public boolean followerInverted() {
    return false;
  }

  @Override
  public CanId canIdFollower() {
    return null;
  }

  public void runIndexer() {
    setVelocity(Constants.IndexerConstants.kFowardVelocity);
  }
  public void runIndexerBreak() {
    setVelocity(RotationsPerSecond.of(0));
  }
  

    @Override
  public void bindCommands() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'bindCommands'");
  }
}
