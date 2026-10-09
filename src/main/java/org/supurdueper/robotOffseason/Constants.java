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
        public static final double kMaxAmps = 60.0;
        public static final CurrentLimitsConfigs kCurrentLimitsIndexer = new CurrentLimitsConfigs()
            .withStatorCurrentLimit(kMaxAmps)
            .withStatorCurrentLimitEnable(true);
        public static final double kP = 5.0;
        public static final double kI = 0;
        public static final double kS = 3.5;
        public static final double kV = 0;
        public static final double kA = 0;
        public static final AngularVelocity kTolerance = RPM.of(60);
        public static final AngularVelocity kFowardVelocity = RPM.of(2000);
        public static final AngularVelocity kBackwardVelocity = RPM.of(-500);
        public static final double kGearRatio = 1.0/3.0;


    }

    public static final class ShooterHoodConstants {
        public static final double kMaxAmps = 40.1;
        public static final CurrentLimitsConfigs kCurrentLimits = new CurrentLimitsConfigs()
            .withStatorCurrentLimit(kMaxAmps)
            .withStatorCurrentLimitEnable(true);
        public static final double gearRatio = (300.0/12.0)*(55.0/12.0);
        public static final Voltage kPeakForwardVoltage = Volts.of(12);
        public static final Voltage kPeakReverseVoltage = Volts.of(-12);
        public static final Angle kZeroPosition = Degrees.of(19);
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
        public static final Angle kSetShotAngle = Degrees.of(20.5);

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

        public static final AngularVelocity kSlowSpeed = RPM.of(300);

        public static final AngularVelocity kFastSpeed = RPM.of(2000);

        public static final double kGearRatio = 3.0/1.0;


    }

    public static final class ShooterConstants {
        public static final double kMaxAmps = 60.0;
        public static final CurrentLimitsConfigs kCurrentLimits = new CurrentLimitsConfigs()
        .withStatorCurrentLimit(kMaxAmps)
        .withStatorCurrentLimitEnable(true);
        public static final AngularVelocity kTolerance = RotationsPerSecond.of(0);
        public static final double kP = 2;
        public static final double kI = 0;
        public static final double kS = 5;
        public static final double kV = 0.035;
        public static final double kA = 0;
        public static final double shooterGearRatio = 30.0/24.0;
        public static final AngularVelocity kIdleRPM = RPM.of(800);
        public static final AngularVelocity kVelocityTolerance = RPM.of(100);
        public static final AngularVelocity kShootRPM = RPM.of(2400);
        public static final AngularVelocity kRevRpm = RPM.of(1200);
        
    }
    
    public class FeederConstants {
        public static final double kP = 5.0;
        public static final double kS = 3.5;
        public static final double kV = 0.0;
        public static final AngularVelocity velocityTolerance = RPM.of(60);
        public static final double kMaxAmps = 60.0;
        public static final double kGearRatio = 24.0 / 24.0;
        public static final CurrentLimitsConfigs kCurrentLimit = new CurrentLimitsConfigs()
                .withStatorCurrentLimit(kMaxAmps)
                .withStatorCurrentLimitEnable(true)
                .withSupplyCurrentLimit(Amps.of(40))
                .withSupplyCurrentLimitEnable(true);
        public static final AngularVelocity feedVelocity = RPM.of(2000);
        public static final AngularVelocity purgeVelocity = RPM.of(-500);
        public static final AngularVelocity idleVelocity = RPM.of(500);
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
            distanceToShooterRPM.put(distanceMeters, velocityRPM);
        }

        static {
            addPointToDistanceToShooterAngle(1.75, 19.0);
            addPointToDistanceToShooterAngle(2.0, 19.0);
            addPointToDistanceToShooterAngle(2.25, 19.0);
            addPointToDistanceToShooterAngle(2.5, 19.0);
            addPointToDistanceToShooterAngle(2.75, 19.0);
            addPointToDistanceToShooterAngle(3.0, 19.0);
            addPointToDistanceToShooterAngle(3.25, 19.0);
            addPointToDistanceToShooterAngle(3.5, 19.0);
            addPointToDistanceToShooterAngle(3.75, 20.5);
            addPointToDistanceToShooterAngle(4.0, 22.0);
            addPointToDistanceToShooterAngle(4.25, 22.0);
            addPointToDistanceToShooterAngle(4.5, 22.0);
            addPointToDistanceToShooterAngle(4.75, 22.0);
            addPointToDistanceToShooterAngle(5, 24.0);

            addPointToDistanceToShooterRPM(1.75, 1825);
            addPointToDistanceToShooterRPM(2.0, 1925);
            addPointToDistanceToShooterRPM(2.25, 2000);
            addPointToDistanceToShooterRPM(2.5, 2050);
            addPointToDistanceToShooterRPM(2.75, 2125);
            addPointToDistanceToShooterRPM(3.0, 2195);
            addPointToDistanceToShooterRPM(3.25, 2275);
            addPointToDistanceToShooterRPM(3.5, 2345);
            addPointToDistanceToShooterRPM(3.75, 2405);
            addPointToDistanceToShooterRPM(4, 2485);
            addPointToDistanceToShooterRPM(4.25, 2625);
            addPointToDistanceToShooterRPM(4.5, 2675);
            addPointToDistanceToShooterRPM(4.75, 2755);
            addPointToDistanceToShooterRPM(5, 2980);
        }
    }

    public static boolean disableHAL = false;

    public static void disableHAL() {
        disableHAL = true;
    }
}
