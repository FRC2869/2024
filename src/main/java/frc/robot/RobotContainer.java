// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.Elevator.DefaultElevatorCommand;
import frc.robot.commands.Elevator.Elevator;
import frc.robot.commands.Intake.DefaultIntakeCommand;
import frc.robot.commands.Intake.Intake;
import frc.robot.commands.Intake.Outtake;
import frc.robot.commands.Shooter.DefaultShooterPivotCommand;
import frc.robot.commands.Shooter.Feed;
import frc.robot.commands.Shooter.Shoot;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.PivotIntakeSubsystem;
import frc.robot.subsystems.PivotShooterSubsystem;


/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very
 * little robot logic should actually be handled in the {@link Robot} periodic
 * methods (other than the scheduler calls).
 * Instead, the structure of the robot (including subsystems, commands, and
 * trigger mappings) should be declared here.
 */
public class RobotContainer {
  enum Position {
    
  }
  // The robot's subsystems and commands are defined here...
  // private final SwerveSubsystem drivebase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(),
      // "swerve"));
  // CommandJoystick rotationController = new CommandJoystick(1);
  // Replace with CommandPS4Controller or CommandJoystick if needed
  CommandJoystick driverController = new CommandJoystick(1);

  // CommandJoystick driverController = new
  // CommandJoystick(3);//(OperatorConstants.DRIVER_CONTROLLER_PORT);
  XboxController driverXbox = new XboxController(0);

  private final ElevatorSubsystem elSubSys = ElevatorSubsystem.getInstance();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    
    // Configure the trigger bindings
    configureBindings();

    // elSubSys.setDefaultCommand(new Elevator());
    elSubSys.setDefaultCommand(new DefaultElevatorCommand());
    PivotIntakeSubsystem.getInstance().setDefaultCommand(new DefaultIntakeCommand());
    PivotShooterSubsystem.getInstance().setDefaultCommand(new DefaultShooterPivotCommand());
    // Applies deadbands and inverts controls because joysticks
    // are back-right positive while robot
    // controls are front-left positive
    // left stick controls translation
    // right stick controls the angular velocity of the robot
    // Command driveFieldOrientedAnglularVelocity = drivebase.driveCommand(
    //     () -> Inputs.getTranslationY(),
    //     () -> Inputs.getTranslationX(),
    //     () -> Inputs.getRotation());

    // Command driveFieldOrientedDirectAngleSim = drivebase.simDriveCommand(
    //     () -> MathUtil.applyDeadband(driverXbox.getLeftY(), OperatorConstants.LEFT_Y_DEADBAND),
    //     () -> MathUtil.applyDeadband(driverXbox.getLeftX(), OperatorConstants.LEFT_X_DEADBAND),
    //     () -> driverXbox.getRawAxis(2));

    // drivebase.setDefaultCommand(
    //     !RobotBase.isSimulation() ? driveFieldOrientedAnglularVelocity : driveFieldOrientedDirectAngleSim);
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be
   * created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
   * an arbitrary predicate, or via the
   * named factories in
   * {@link edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses
   * for
   * {@link CommandXboxController
   * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller PS4}
   * controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick
   * Flight joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`

    // new JoystickButton(driverXbox, 1).onTrue((new InstantCommand(drivebase::zeroGyro)));
    // new JoystickButton(driverXbox, 3).onTrue(new InstantCommand(drivebase::lock));
    // new JoystickButton(driverXbox, 3).whileTrue(new RepeatCommand(new
    // InstantCommand(drivebase::lock, drivebase)));
    Inputs.getShoot().whileTrue(new Shoot());
    Inputs.getIntake().whileTrue(new Intake());
    Inputs.getOuttake().whileTrue(new Outtake());
    Inputs.getFeed().whileTrue(new Feed());
    Inputs.elevatorMax().whileTrue(new Elevator());
    Inputs.elevatorMin().whileTrue(new Elevator());
    // Inputs.getLimelight().onTrue(new TrackAprilTag());
    
    // Inputs.elevatorMax().onTrue(new Elevator());
    // Inputs.elevatorMin().onTrue(new Elevator());
    // Inputs.getOverride().onTrue(new Elevator());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
    // An example command will be run in autonomous
    // return drivebase.getAutonomousCommand("New Path", true);
  }

  public void setDriveMode() {
    // drivebase.setDefaultCommand();
  }

  public void setMotorBrake(boolean brake) {
    // drivebase.setMotorBrake(brake);
  }
}
