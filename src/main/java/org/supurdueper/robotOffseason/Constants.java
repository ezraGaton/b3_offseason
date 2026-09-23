// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.supurdueper.robotOffseason;
import static edu.wpi.first.units.Units.*;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.signals.GravityTypeValue;

import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.interpolation.InterpolatingDoubleTreeMap;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularAcceleration;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.units.measure.Voltage;
import org.supurdueper.lib.utils.ExpCurve;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final double loopPeriodSecs = 0.02;
    public static boolean tuningMode = false;
    public static boolean publishToNT = true;
    public static CANBus canivoreBus = new CANBus("canivore");
    public static CANBus rioBus = new CANBus("rio");

    public static final class DriverConstants {
        public static final int kControllerPort = 0;
        public static final double kDeadzone = 0.1;
        public static final ExpCurve kLeftStickCurve = new ExpCurve(2.0, 0, 1, kDeadzone);
        public static final ExpCurve kRightStickCurve = new ExpCurve(2.0, 0, 1, kDeadzone);
        public static final ExpCurve kTriggerCurve = new ExpCurve(1, 0, 1, kDeadzone);
        public static final double kSlowModeScalor = 0.85;
        public static final double kDefaultTurnScalor = 0.75;
        public static final double kTurboModeScalor = 1;
    }

    public static final class IntakeConstants {
        public static final double kMaxAmps = 50.0;
        public static final CurrentLimitsConfigs kCurrentLimits = new CurrentLimitsConfigs()
            .withStatorCurrentLimit(kMaxAmps)
            .withStatorCurrentLimitEnable(true);
        public static final double kP = 0;
        public static final double kI = 0;
        public static final double kS = 0;
        public static final double kV = 0;
        public static final double kA = 0;
        public static final AngularVelocity kTolerance = RotationsPerSecond.of(0);
        public static final AngularVelocity kFowardVelocity = RotationsPerSecond.of(3);
        public static final double kGearRatio = 0;
        public static final AngularVelocity kPurgeVelocity = RotationsPerSecond.of(-3);
        
    }

    public static final class IndexerConstants {
        public static final double kMaxAmps = 50.0;
        public static final CurrentLimitsConfigs kCurrentLimitsIndexer = new CurrentLimitsConfigs()
            .withStatorCurrentLimit(kMaxAmps)
            .withStatorCurrentLimitEnable(true);
        public static final double kP = 0;
        public static final double kI = 0;
        public static final double kS = 0;
        public static final double kV = 0;
        public static final double kA = 0;
        public static final AngularVelocity kTolerance = RotationsPerSecond.of(0);
        public static final AngularVelocity kFowardVelocity = RotationsPerSecond.of(0);
        public static final AngularVelocity kBackwardVelocity = RotationsPerSecond.of(0);


    }

    public static final class RollerFloorConstants {

        public static final double kMaxAmps = 50.0;

        public static final CurrentLimitsConfigs kCurrentLimits =  new CurrentLimitsConfigs()
        .withStatorCurrentLimit(kMaxAmps)
        .withStatorCurrentLimitEnable(true);

        public static final double kP = 0;

        public static final double kI = 0;

        public static final double kS = 0;

        public static final double kA = 0;

        public static final double kV = 0;

        public static final double kTolerance = 0;

        public static final AngularVelocity kSlowSpeed = null;

        public static final AngularVelocity kFastSpeed = null;;


    }

    public static final class ShooterConstants {
        public static final double kMaxAmps = 50.0;
        public static final CurrentLimitsConfigs kCurrentLimits = new CurrentLimitsConfigs()
        .withStatorCurrentLimit(kMaxAmps)
        .withStatorCurrentLimitEnable(true);
        public static final AngularVelocity kTolerance = RotationsPerSecond.of(0);
        public static final double kP = 0;
        public static final double kI = 0;
        public static final double kS = 0;
        public static final double kV = 0;
        public static final double kA = 0;
        public static final AngularVelocity kShootSpeed = RotationsPerSecond.of(0);
        public static final double shooterGearRatio = 0;
        
    }

    public static final class IntakePivotConstants{
        public static final double kMaxAmps = 50.0;
        public static final CurrentLimitsConfigs kCurrentLimits = new CurrentLimitsConfigs()
        .withStatorCurrentLimit(kMaxAmps)
        .withStatorCurrentLimitEnable(true);
        public static final double kP = 0;
        public static final double kI = 0;
        public static final double kS = 0;
        public static final double kV = 0;
        public static final double kA = 0;
        public static final double kG = 0;
        public static final Angle positionTolerance = null;
        public static final Angle kFowardSoftLimit = null;
        public static final Angle kBackwardSoftLimit = null;
    }

    public static boolean disableHAL = false;

    public static void disableHAL() {
        disableHAL = true;
    }
}
