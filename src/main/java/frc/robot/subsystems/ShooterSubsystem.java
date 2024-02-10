package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;;

public class ShooterSubsystem extends SubsystemBase {
    
    private static ShooterSubsystem shoot = null;

    private TalonFX leftShooter;
    private TalonFX rightShooter;
    private CANSparkMax feeder;

    public ShooterSubsystem() {
        leftShooter = new TalonFX(ShooterConstants.leftID);
        rightShooter = new TalonFX(ShooterConstants.rightID);
        feeder = new CANSparkMax(ShooterConstants.feederID, MotorType.kBrushless);
        //init();
    }

    public static ShooterSubsystem getInstance() {
        if (shoot == null) shoot = new ShooterSubsystem();
        return shoot;
    }
    
    // public void init() {
    //     TalonFXConfigurator configs = leftTalon.getConfigurator();
    //     Slot0Configs slotCons = new Slot0Configs();
    //     slotCons.kP = Constants.ShooterConstants.kP;
    //     slotCons.kI = Constants.ShooterConstants.kI;
    //     slotCons.kD = Constants.ShooterConstants.kD;
    //     configs.apply(slotCons);
    // }
    public void rev() {
        leftShooter.set(.5);
        rightShooter.set(.5);
    }

    public void shoot() {
        leftShooter.set(.5);
        rightShooter.set(.5);
        feeder.set(1);
    }
    
    public void intake() {
        feeder.set(.25);
    }

    public void stopFeeder(){
        feeder.set(0);
    }

    public void stopShooter(){
        leftShooter.set(0);
        rightShooter.set(0);
    }
}