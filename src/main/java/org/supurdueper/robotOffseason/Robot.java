// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.supurdueper.robotOffseason;

import org.supurdueper.BuildConstants;
import org.supurdueper.lib.LoggedTunableNumber;
import org.supurdueper.lib.subsystems.SupurdueperRobot;
import org.supurdueper.robotOffseason.autos.AutoRoutines;
import org.supurdueper.robotOffseason.state.RobotStates;
import org.supurdueper.robotOffseason.subsystems.Vision;
import org.supurdueper.robotOffseason.utils.FieldConstants;

import com.ctre.phoenix6.HootAutoReplay;


import choreo.auto.AutoChooser;
import choreo.auto.AutoFactory;
import dev.doglog.DogLog;
import dev.doglog.DogLogOptions;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Threads;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the Main.java file in the project.
 */
public class Robot extends SupurdueperRobot {
    @SuppressWarnings("unused")
    private final RobotContainer m_robotContainer;

    /* Path follower */
    private final AutoFactory autoFactory;
    private final AutoRoutines autoRoutines;
    private final AutoChooser autoChooser = new AutoChooser();
    private final LoggedTunableNumber autoTimeout;
  
    /* log and replay timestamp and joystick data */
    private final HootAutoReplay m_timeAndJoystickReplay =
            new HootAutoReplay().withTimestampReplay().withJoystickReplay();

    /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  public Robot() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    autoFactory = RobotContainer.getDrivetrain().createAutoFactory();
    autoRoutines = new AutoRoutines(autoFactory);
    autoTimeout = new LoggedTunableNumber("Auto Timeout", 0.0);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
        Threads.setCurrentThreadPriority(true,1);
        CommandScheduler.getInstance().run();

  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
    Vision.setDisabled();
  }

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    Vision.setEnabled();
    Vision.setAprilTagFilter();
    Vision.updateIMUMode();
    autoChooser.selectedCommandScheduler().schedule();
    
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    resetCommandsAndButtons();
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    resetCommandsAndButtons();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  @Override 
  public void robotInit() {
    DogLog.setOptions(new DogLogOptions()
                .withLogExtras(false)
                .withCaptureDs(false)
                .withNtPublish(Constants.publishToNT)
                .withCaptureNt(false));
        // Record metadata
        DogLog.log("Git/ProjectName", BuildConstants.MAVEN_NAME);
        DogLog.log("Git/BuildDate", BuildConstants.BUILD_DATE);
        DogLog.log("Git/GitSHA", BuildConstants.GIT_SHA);
        DogLog.log("Git/GitDate", BuildConstants.GIT_DATE);
        DogLog.log("Git/GitBranch", BuildConstants.GIT_BRANCH);
        switch (BuildConstants.DIRTY) {
            case 0:
                DogLog.log("Git/GitDirty", "All changes committed");
                break;
            case 1:
                DogLog.log("Git/GitDirty", "Uncomitted changes");
                break;
            default:
                DogLog.log("Git/GitDirty", "Unknown");
                break;
  }
  resetCommandsAndButtons();
}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}

  public void resetCommandsAndButtons() {
    CommandScheduler.getInstance().cancelAll(); // Disable any currently running commands
    CommandScheduler.getInstance().getActiveButtonLoop().clear();

        // Bind Triggers for all subsystems
    bindCommands();
  }

}
