// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.Utils;
import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModule.DriveRequestType;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.ElevatorConstants.ElevatorPosition;
import frc.robot.commands.StopIntakePivot;
import frc.robot.commands.StopShooterPivot;
import frc.robot.commands.Elevator.DefaultElevatorCommand;
import frc.robot.commands.Elevator.Elevator;
import frc.robot.commands.Elevator.ElevatorCommand;
import frc.robot.commands.Elevator.SetElevatorSpeed;
import frc.robot.commands.Elevator.StopElevator;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.Elevator.DefaultElevatorCommand;
// import frc.robot.commands.Elevator.Elevator;
import frc.robot.commands.Intake.DefaultIntakeCommand;
import frc.robot.commands.Intake.Intake;
import frc.robot.commands.Intake.Outtake;
import frc.robot.commands.Intake.StopIntake;
import frc.robot.commands.Shooter.DefaultShooterPivotCommand;
import frc.robot.commands.Shooter.Feed;
import frc.robot.commands.Shooter.Shoot;
import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.PivotIntakeSubsystem;
import frc.robot.subsystems.PivotShooterSubsystem;

public class RobotContainer {

  private final ElevatorSubsystem elSubSys = ElevatorSubsystem.getInstance();

  private double MaxSpeed = 6; // 6 meters per second desired top speed
  private double MaxAngularRate = 1.5 * Math.PI; // 3/4 of a rotation per second max angular velocity

  /* Setting up bindings for necessary control of the swerve drive platform */
  private final CommandXboxController joystick = new CommandXboxController(0); // My joystick
  private final CommandSwerveDrivetrain drivetrain = TunerConstants.DriveTrain; // My drivetrain

  private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
      .withDeadband(MaxSpeed * 0.1).withRotationalDeadband(MaxAngularRate * 0.1) // Add a 10% deadband
      .withDriveRequestType(DriveRequestType.OpenLoopVoltage); // I want field-centric
                                                               // driving in open loop
  private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();
  private final SwerveRequest.PointWheelsAt point = new SwerveRequest.PointWheelsAt();
  private final Telemetry logger = new Telemetry(MaxSpeed);

  private void configureBindings() {

    // new JoystickButton(driverXbox, 1).onTrue((new InstantCommand(drivebase::zeroGyro)));
    // new JoystickButton(driverXbox, 3).onTrue(new InstantCommand(drivebase::lock));
    // new JoystickButton(driverXbox, 3).whileTrue(new RepeatCommand(new
    // InstantCommand(drivebase::lock, drivebase)));
    //Intake + Shoot
    Inputs.getIntakeIn().whileTrue(new Intake());
    Inputs.getIntakeOut().whileTrue(new Outtake());
    Inputs.getStopIntake().whileTrue(new StopIntake());
    Inputs.getShoot().whileTrue(new Shoot());
    Inputs.getFeed().whileTrue(new Feed());
    Inputs.getStopIntakePivot().onTrue(new StopIntakePivot());
    Inputs.getStopShooterPivot().onTrue(new StopShooterPivot());
    Inputs.getStopIntake().onTrue(new ParallelCommandGroup(new StopIntakePivot(), new StopShooterPivot()));

    //Elevator
    Inputs.getElevatorBasePos().whileTrue(new ElevatorCommand(ElevatorPosition.STARTING));
    Inputs.getElevatorAmpPos().whileTrue(new ElevatorCommand(ElevatorPosition.AMP));
    Inputs.getElevatorSpeakerPos().whileTrue(new ElevatorCommand(ElevatorPosition.SPEAKER));
    Inputs.getElevatorTransferPos().whileTrue(new ElevatorCommand(ElevatorPosition.TRANSFER));
    Inputs.getStopElevator().onTrue(new StopElevator());
    Inputs.getElevatorDown().onTrue(new SetElevatorSpeed(-.5));
    Inputs.getElevatorUp().onTrue(new SetElevatorSpeed(.5));

    Inputs.elevatorMax().whileTrue(new Elevator());
    Inputs.elevatorMin().whileTrue(new Elevator());

    // Inputs.getLimelight().onTrue(new TrackAprilTag());
    
    // Inputs.elevatorMax().onTrue(new Elevator());
    // Inputs.elevatorMin().onTrue(new Elevator());
    // Inputs.getOverride().onTrue(new Elevator());
    // Inputs.elevatorMax().whileTrue(new Elevator());
    // Inputs.elevatorMin().whileTrue(new Elevator());

    drivetrain.setDefaultCommand( // Drivetrain will execute this command periodically
        drivetrain.applyRequest(() -> drive.withVelocityX(-joystick.getLeftY() * MaxSpeed) // Drive forward with
                                                                                           // negative Y (forward)
            .withVelocityY(-joystick.getLeftX() * MaxSpeed) // Drive left with negative X (left)
            .withRotationalRate(-joystick.getRightX() * MaxAngularRate) // Drive counterclockwise with negative X (left)
        ));

    joystick.leftBumper().whileTrue(drivetrain.applyRequest(() -> brake));
    joystick.rightBumper().whileTrue(drivetrain
        .applyRequest(() -> point.withModuleDirection(new Rotation2d(-joystick.getLeftY(), -joystick.getLeftX()))));

    // reset the field-centric heading on left bumper press
    joystick.leftBumper().onTrue(drivetrain.runOnce(() -> drivetrain.seedFieldRelative()));

    if (Utils.isSimulation()) {
      drivetrain.seedFieldRelative(new Pose2d(new Translation2d(), Rotation2d.fromDegrees(90)));
    }
    drivetrain.registerTelemetry(logger::telemeterize);
  }

  public RobotContainer() {
    elSubSys.setDefaultCommand(new DefaultElevatorCommand());
    PivotIntakeSubsystem.getInstance().setDefaultCommand(new DefaultIntakeCommand());
    PivotShooterSubsystem.getInstance().setDefaultCommand(new DefaultShooterPivotCommand());

    configureBindings();
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
