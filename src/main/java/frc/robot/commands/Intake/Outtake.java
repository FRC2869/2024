// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Inputs;
import frc.robot.subsystems.IntakeSubsystem;

//REVIEW: Make this an Instant Command
public class Outtake extends Command {

  private final IntakeSubsystem intake = IntakeSubsystem.getInstance();
  //Scanner scan = new Scanner(System.in);
  /** Creates a new Intake. */
  public Outtake() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intake.outtake();
    // System.out.println("Is your name Jackson? (y/n)");
    // String input = scan.nextLine();
    // if(input.equals("y")) {
    // } 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
