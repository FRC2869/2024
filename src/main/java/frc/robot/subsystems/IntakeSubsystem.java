// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

//REIVEW: Add a function to see if a game piece is in the intake
public class IntakeSubsystem extends SubsystemBase {

  private static IntakeSubsystem instance;
  /** Creates a new IntakeSubsystem. */
  private CANSparkMax rightMotor;
  private CANSparkMax leftMotor;
  
  public IntakeSubsystem() {
    leftMotor = new CANSparkMax(IntakeConstants.intakeID1, MotorType.kBrushed);
    rightMotor = new CANSparkMax(IntakeConstants.intakeID2, MotorType.kBrushed);
  }

  public static IntakeSubsystem getInstance() {
    if (instance == null) instance = new IntakeSubsystem();
    return instance;
  }

  public void intake() {
      leftMotor.set(.5);
      rightMotor.set(-.5);
  }

  public void outtake() {
      leftMotor.set(-.5);
      rightMotor.set(.5);
  }

  public void stop() {
    leftMotor.set(0);
    rightMotor.set(0);
  }


}
