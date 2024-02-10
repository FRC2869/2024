// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import java.util.Timer;

import edu.wpi.first.hal.simulation.ConstBufferCallback;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Inputs;
import frc.robot.subsystems.ShooterSubsystem;

public class Shoot extends Command {

  ShooterSubsystem shooter = ShooterSubsystem.getInstance();
  private boolean hasRun;
  private double startTime;
  /** Creates a new Shoot. */
  public Shoot() {
    addRequirements(shooter);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(!hasRun) {
      hasRun = true;
      startTime = Constants.timer.get();
    }
    //REVIEW: Don't forget you have to rev the shooter and make sure it is at the right speed before shooting.
    shooter.rev();
    if(Constants.timer.get()-startTime>3){
      shooter.shoot();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.stopFeeder();
    shooter.stopShooter();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {//REVIEW: You can do this by just using the .whileTrue() on teh trigger when you use it.
    return false;
  }
}
