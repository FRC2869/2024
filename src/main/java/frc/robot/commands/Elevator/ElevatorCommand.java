// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Constants.ElevatorConstants.ElevatorPosition;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorCommand extends Command {
  ElevatorSubsystem elevator = ElevatorSubsystem.getInstance();
  private double position; 
  /** Creates a new ElevatorCommand. */
  public ElevatorCommand(ElevatorPosition position) {
    addRequirements(elevator);
    switch (position) {
      case STARTING:
        this.position = Constants.ElevatorConstants.startingPosition;
        return;
      case FLOOR:
        this.position = Constants.ElevatorConstants.floorPosition;
        return;
      case STORAGE1:
        this.position = Constants.ElevatorConstants.storagePosition;
        return;
      case TRANSFER:
        this.position = Constants.ElevatorConstants.transferPosition;
        return;
      case STORAGE2:
        this.position = Constants.ElevatorConstants.storagePosition;
        return;
      default:
        this.position = 0;
    }
    // Use addRequirements() here to declare subsystem dependencies.
  }
  public ElevatorCommand(double position) {
    this.position = position;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    elevator.setPosition(position);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (elevator.getPosition() == position) return true;
    return false;
  }
}
