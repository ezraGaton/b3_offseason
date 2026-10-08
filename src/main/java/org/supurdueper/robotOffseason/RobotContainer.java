// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.supurdueper.robotOffseason;

import edu.wpi.first.wpilibj2.command.Commands;
import lombok.Getter;

import org.supurdueper.robotOffseason.state.Driver;
import org.supurdueper.robotOffseason.state.RobotStates;

import org.supurdueper.robotOffseason.subsystems.Indexer;
import org.supurdueper.robotOffseason.subsystems.IntakePivot;
import org.supurdueper.robotOffseason.subsystems.Rollerfloor;
import org.supurdueper.robotOffseason.subsystems.Shooter;
import org.supurdueper.robotOffseason.subsystems.ShooterHood;
import org.supurdueper.robotOffseason.subsystems.Vision;
import org.supurdueper.robotOffseason.subsystems.intakeBack;
import org.supurdueper.robotOffseason.subsystems.intakeFront;
import org.supurdueper.robotOffseason.subsystems.drive.Drivetrain;
import org.supurdueper.robotOffseason.subsystems.drive.generated.TunerConstants;



/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

  @Getter
    private static Driver testController;

  @Getter
    private static Driver driver;

  @Getter
   private static Vision vision;

  @Getter
    private static Drivetrain drivetrain;

  @Getter
    private static Rollerfloor rollerfloor;
  
  @Getter
    private static Indexer indexer;
  
  @Getter
   private static Shooter shooter;

  @Getter
    private static intakeFront intakeFront;

  @Getter
    private static intakeBack intakeBack;

  @Getter
    private static IntakePivot intakePivot;

  @Getter
    private static ShooterHood shooterHood;


  // The robot's subsystems and commands are defined here...
  //private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  //private final CommandXboxController m_driverController =
  //    new CommandXboxController(DriverConstants.kControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    vision = new Vision();
    driver = new Driver(0);
    drivetrain = TunerConstants.createDrivetrain();
 //   intake = new intake();
    testController = new Driver(2);
    rollerfloor = new Rollerfloor();
    indexer = new Indexer();
    shooter = new Shooter();
    intakeFront = new intakeFront();
    intakeBack = new intakeBack();
    intakePivot = new IntakePivot();
    shooterHood = new ShooterHood();

    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    //new Trigger(m_exampleSubsystem::exampleCondition)
    //    .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    //m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
  }

}
