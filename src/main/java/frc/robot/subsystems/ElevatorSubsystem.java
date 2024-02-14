package frc.robot.subsystems;

import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;

public class ElevatorSubsystem extends SubsystemBase {
    
    private static ElevatorSubsystem elev;

    private CANSparkMax elevator1;
    private CANSparkMax elevator2;

    private RelativeEncoder encoder1;
    private RelativeEncoder encoder2;

    private boolean posControl;

    private double pos;

    private SparkPIDController pid1;

    private SparkPIDController pid2;

    private double speed;

    public ElevatorSubsystem () {
        elevator1 =  new CANSparkMax(18, MotorType.kBrushless);
        elevator2 =  new CANSparkMax(19, MotorType.kBrushless);
        encoder1 = elevator1.getEncoder();
        encoder2 = elevator2.getEncoder();
        // elevator1.follow(elevator2, true);
        configureMotors();
        //REVIEW: Look at 2023 code and add the initalize sparks function
    }

    private void configureMotors() {
		elevator1.restoreFactoryDefaults();
        elevator1.setInverted(ElevatorConstants.Motor1.inverted);
        elevator1.setSmartCurrentLimit(ElevatorConstants.Motor1.currentLimit);
        elevator1.setIdleMode(ElevatorConstants.Motor1.idleMode);


		pid1 = elevator1.getPIDController();
        pid1.setP(ElevatorConstants.kP);
        pid1.setI(ElevatorConstants.kI);
        pid1.setD(ElevatorConstants.kD);
        pid1.setIZone(ElevatorConstants.kIz);
        pid1.setFF(ElevatorConstants.kF);
        pid1.setOutputRange(-ElevatorConstants.kMaxPower, ElevatorConstants.kMaxPower);
        encoder1.setPositionConversionFactor(ElevatorConstants.gearRatio);
        encoder1.setPosition(ElevatorConstants.startingPosition);

        elevator1.burnFlash();

        elevator2.restoreFactoryDefaults();
        elevator2.setInverted(ElevatorConstants.Motor2.inverted);
        elevator2.setSmartCurrentLimit(ElevatorConstants.Motor2.currentLimit);
        elevator2.setIdleMode(ElevatorConstants.Motor2.idleMode);


		pid2 = elevator2.getPIDController();
        pid2.setP(ElevatorConstants.kP);
        pid2.setI(ElevatorConstants.kI);
        pid2.setD(ElevatorConstants.kD);
        pid2.setIZone(ElevatorConstants.kIz);
        pid2.setFF(ElevatorConstants.kF);
        pid2.setOutputRange(-ElevatorConstants.kMaxPower, ElevatorConstants.kMaxPower);
        encoder2.setPositionConversionFactor(ElevatorConstants.gearRatio);
        encoder2.setPosition(ElevatorConstants.startingPosition);

        elevator2.burnFlash();  
        
        elevator1.follow(elevator2, true);
	}

    public static ElevatorSubsystem getInstance() {
        if (elev == null) elev = new ElevatorSubsystem();
        return elev;
    }
    //REVIEW: Format your subsystems like this. Have functions write their values to variables and only do .set once in periodic.
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setPosition(double pos) {
        this.pos = pos;
    }

    public void setEncoderPosition() {
        encoder1.setPosition(ElevatorConstants.startingPosition);
        encoder2.setPosition(ElevatorConstants.startingPosition);
    }

    public void setPositionControl(boolean posControl){
        this.posControl = posControl;
    }

    public double getPosition() {
        return (encoder1.getPosition()+encoder2.getPosition())/2;
    }

    @Override
    public void periodic() {
        if(posControl){
            //This is how to set position on Sparks
            elevator2.getPIDController().setReference(pos, ControlType.kPosition);
        }else{
            elevator2.set(speed);
        }
    }
}
