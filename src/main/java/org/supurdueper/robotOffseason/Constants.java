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
    public static boolean tuningMode = true;
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
    
    public static final class DriveConstants {
        public static final double headingKp = 15.0;
        public static final double headingKi = 0.0;
        public static final double headingKd = 0.2;
        public static final double translationKp = 7.0;
        public static final double translationKi = 0;
        public static final double translationKd = 0.1;
        public static final AngularVelocity rotationClosedLoopDeadband = RadiansPerSecond.of(0.00);
        public static final LinearVelocity translationClosedLoopDeadband = MetersPerSecond.of(0.01);
        public static final Translation2d robotToBumperCenter = null;
    }

    public static final class IntakeFrontConstants {
        public static final double kMaxAmps = 50.0;
        public static final CurrentLimitsConfigs kCurrentLimits = new CurrentLimitsConfigs()
            .withStatorCurrentLimit(kMaxAmps)
            .withStatorCurrentLimitEnable(true);
        public static final double kP = 2.5;
        public static final double kI = 0;
        public static final double kS = 2.5;
        public static final double kV = 0.03;
        public static final double kA = 0;
        public static final AngularVelocity kTolerance = RotationsPerSecond.of(0);
        public static final AngularVelocity kFowardVelocity = RotationsPerSecond.of(80);
        public static final double kGearRatio = 24.0/12.0;
        public static final AngularVelocity kPurgeVelocity = RotationsPerSecond.of(-3);
        
    }

    public static final class IntakeBackConstants {
        public static final double kMaxAmps = 50.0;
        public static final CurrentLimitsConfigs kCurrentLimits = new CurrentLimitsConfigs()
            .withStatorCurrentLimit(kMaxAmps)
            .withStatorCurrentLimitEnable(true);
        public static final double kP = 2.5;
        public static final double kI = 0;
        public static final double kS = 2.5;
        public static final double kV = 0;
        public static final double kA = 0.03;
        public static final AngularVelocity kTolerance = RotationsPerSecond.of(0);
        public static final AngularVelocity kFowardVelocity = RotationsPerSecond.of(10);
        public static final double kGearRatio = 24.0/12.0;
        public static final AngularVelocity kPurgeVelocity = RotationsPerSecond.of(-3);
        
    }

    public static final class IndexerConstants {
        public static final double kMaxAmps = 50.0;
        public static final CurrentLimitsConfigs kCurrentLimitsIndexer = new CurrentLimitsConfigs()
            .withStatorCurrentLimit(kMaxAmps)
            .withStatorCurrentLimitEnable(true);
        public static final double kP = 1.2;
        public static final double kI = 0;
        public static final double kS = 1.85;
        public static final double kV = 0;
        public static final double kA = 0;
        public static final AngularVelocity kTolerance = RotationsPerSecond.of(0);
        public static final AngularVelocity kFowardVelocity = RPM.of(3000);
        public static final AngularVelocity kBackwardVelocity = RotationsPerSecond.of(0);
        public static final double kGearRatio = 1.0/3.0;


    }

    public static final class ShooterHoodConstants {
        public static final double kMaxAmps = 20.0;
        public static final CurrentLimitsConfigs kCurrentLimits = new CurrentLimitsConfigs()
            .withStatorCurrentLimit(kMaxAmps)
            .withStatorCurrentLimitEnable(true);
        public static final double gearRatio = (300.0/12.0)*(55.0/12.0);
        public static final Voltage kPeakForwardVoltage = Volts.of(12);
        public static final Voltage kPeakReverseVoltage = Volts.of(-12);
        public static final Angle kZeroPosition = Degrees.of(18);
        public static final double kP = 500;
        public static final double kI = 0;
        public static final double kD = 0;
        public static final double kA = 0;
        public static final double kG = 0;
        public static final double kS = 0;
        public static final double kV = 0;
        public static final double profileKa = 0;
        public static final double profileKv = 0;
        public static final AngularVelocity profileV = RotationsPerSecond.of(0);
        public static final AngularAcceleration profileA = RotationsPerSecondPerSecond.of(0);
        public static final Angle kFowardSoftLimit = Degrees.of(29);
        public static final Angle kBackwardSoftLimit = Degrees.of(19);
        public static final Angle positionTolerance = Degrees.of(0.3);

    }

    public static final class RollerFloorConstants {

        public static final double kMaxAmps = 50.0;

        public static final CurrentLimitsConfigs kCurrentLimits =  new CurrentLimitsConfigs()
        .withStatorCurrentLimit(kMaxAmps)
        .withStatorCurrentLimitEnable(true);

        public static final double kP = 5;

        public static final double kI = 0;

        public static final double kS = 10.5;

        public static final double kA = 0;

        public static final double kV = 0.08;

        public static final double kTolerance = 0;

        public static final AngularVelocity kSlowSpeed = RotationsPerSecond.of(10);

        public static final AngularVelocity kFastSpeed = RotationsPerSecond.of(100);

        public static final double kGearRatio = 1/3;


    }

    public static final class ShooterConstants {
        public static final double kMaxAmps = 50.0;
        public static final CurrentLimitsConfigs kCurrentLimits = new CurrentLimitsConfigs()
        .withStatorCurrentLimit(kMaxAmps)
        .withStatorCurrentLimitEnable(true);
        public static final AngularVelocity kTolerance = RotationsPerSecond.of(0);
        public static final double kP = 2;
        public static final double kI = 0;
        public static final double kS = 5;
        public static final double kV = 0.035;
        public static final double kA = 0;
        public static final AngularVelocity kShootSpeed = RotationsPerSecond.of(33.33);
        public static final double shooterGearRatio = 0.8;
        
    }

    public static final class IntakePivotConstants{
        public static final double kMaxAmps = 40.0;
        public static final CurrentLimitsConfigs kCurrentLimits = new CurrentLimitsConfigs()
        .withStatorCurrentLimit(kMaxAmps)
        .withStatorCurrentLimitEnable(true);
        public static final double kP = 0;
        public static final double kI = 0;
        public static final double kS = 0;
        public static final double kV = 0;
        public static final double kA = 0;
        public static final double kG = 0;
        public static final Angle positionTolerance = Degrees.of(1);
        public static final Angle kFowardSoftLimit = Degrees.of(123);
        public static final Angle kBackwardSoftLimit = Degrees.of(-1);
        public static final Angle kZeroPosition = Degrees.of(122);
        public static final Voltage kPeakReverseVoltage = Volts.of(-12);
        public static final Voltage kPeakForwardVoltage = Volts.of(12);
        public static final double gearRatio = (25.0 / 1.0) * (36.0 / 24.0);
    }

     public class LookupTables {

        public static final InterpolatingDoubleTreeMap distanceToShooterAngle = new InterpolatingDoubleTreeMap();
        public static final InterpolatingDoubleTreeMap distanceToShooterRPM = new InterpolatingDoubleTreeMap();

        private static void addPointToDistanceToShooterAngle(double distanceMeters, double angleDegrees) {
            distanceToShooterAngle.put(distanceMeters, angleDegrees);
        }

        private static void addPointToDistanceToShooterRPM(double distanceMeters, double velocityRPM) {
            distanceToShooterAngle.put(distanceMeters, velocityRPM);
            distanceToShooterRPM.put(distanceMeters, velocityRPM);
        }

        static {
            addPointToDistanceToShooterAngle(00, 51.0);

            addPointToDistanceToShooterRPM(00, 1600);
            addPointToDistanceToShooterAngle(1.6, 19.0);
            addPointToDistanceToShooterAngle(2.0, 19.0);
            addPointToDistanceToShooterAngle(2.25, 20.0);
            addPointToDistanceToShooterAngle(2.5, 22.0);
            addPointToDistanceToShooterAngle(2.75, 24.0);
            addPointToDistanceToShooterAngle(3.0, 26.5);
            addPointToDistanceToShooterAngle(3.25, 28.0);
            addPointToDistanceToShooterAngle(3.5, 29.0);
            addPointToDistanceToShooterAngle(5.3, 32.0);

            addPointToDistanceToShooterRPM(1.6, 1550);
            addPointToDistanceToShooterRPM(2.0, 1650);
            addPointToDistanceToShooterRPM(2.25, 1700);
            addPointToDistanceToShooterRPM(2.5, 1725);
            addPointToDistanceToShooterRPM(2.75, 1725);
            addPointToDistanceToShooterRPM(3.0, 1750);
            addPointToDistanceToShooterRPM(3.25, 1775);
            addPointToDistanceToShooterRPM(3.5, 1825);
            addPointToDistanceToShooterRPM(5.3, 2050);
        }
    }

    public static boolean disableHAL = false;

    public static void disableHAL() {
        disableHAL = true;
    }
}
