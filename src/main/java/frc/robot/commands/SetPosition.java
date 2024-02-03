// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.swervedrive.ElevatorSubsystem;

public class SetPosition extends Command {
  private ElevatorSubsystem elevator = ElevatorSubsystem.getInstance();
  
  private double pos;
  private double s;

  /** Creates a new goToPosition. */
  public SetPosition(double position, double speed) {
    addRequirements(elevator);
    s = speed;
    pos = position;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (elevator.getPosition() < pos) {
      elevator.setSpeed(s);
    }
    else if (elevator.getPosition() > pos) {
      elevator.setSpeed(-s);
    } else {
      elevator.setSpeed(0);
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if ((elevator.getPosition() == pos) || (elevator.getPosition() >= ElevatorConstants.maxHeight) || (elevator.getPosition <= ElevatorConstants.minHeight)) {
      return true;
    } else {
      return false;
    }
  }
}
