package frc.robot.subsystems.swervedrive;

import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.hardware.TalonFX;

import frc.robot.Constants;

public class ShooterSubsystem {
    
    private static ShooterSubsystem shoot = null;

    private TalonFX leftTalon;
    private TalonFX rightTalon;

    public ShooterSubsystem() {
        leftTalon = new TalonFX(Constants.ShooterConstants.leftID);
        rightTalon = new TalonFX(Constants.ShooterConstants.rightID);
    }

    public static ShooterSubsystem getInstance() {
        if (shoot == null) shoot = new ShooterSubsystem();
        return shoot;
    }
    
    public void init() {
        TalonFXConfigurator configs = leftTalon.getConfigurator();
    }

    public void shoot() {
        leftTalon.setVoltage(5);
    }
    
    public void intake() {
        leftTalon.set(.2);
        rightTalon.set(-.2);
    }
}