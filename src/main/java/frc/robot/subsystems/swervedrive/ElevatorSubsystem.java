package frc.robot.subsystems.swervedrive;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ElevatorSubsystem extends SubsystemBase {
    
    private static ElevatorSubsystem elev;

    private CANSparkMax csm;
    private SparkPIDController CANpid;

    public ElevatorSubsystem () {
        csm =  new CANSparkMax(Constants.ElevatorConstants.motorID, MotorType.kBrushless);
        CANpid = csm.getPIDController();
    }

    public static ElevatorSubsystem getInstance() {
        if (elev == null) elev = new ElevatorSubsystem();
        return elev;
    }

    public void init() {
        CANpid.setP(Constants.ElevatorConstants.kP);
        CANpid.setI(Constants.ElevatorConstants.kI);
        CANpid.setD(Constants.ElevatorConstants.kD);
    }

    public void goUp() {
        csm.set(Constants.ElevatorConstants.speed);
    }

    public void goDown() {
        csm.set(-Constants.ElevatorConstants.speed);
    }
}
