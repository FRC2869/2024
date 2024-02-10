package frc.robot.subsystems;

import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkFlex;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.IntakeConstants.PositionsIntake;

public class PivotShooterSubsystem extends SubsystemBase {
    private static PivotShooterSubsystem pivot;

    private SparkPIDController pid;
    private CANSparkFlex pivotMotor;
    private double pos;
    private RelativeEncoder encoder;
    private boolean isPosControl = false;

    private double speed;

    private Object currentPos;

    public static PivotShooterSubsystem getInstance() {
        if (pivot == null) pivot = new PivotShooterSubsystem();
        return pivot;
    }

    public PivotShooterSubsystem() {
        pivotMotor = new CANSparkFlex(IntakeConstants.PivotID, MotorType.kBrushless);
        isPosControl = false;
        encoder = pivotMotor.getEncoder();
        configurePivotMotor();
    }

	private void configurePivotMotor() {
		// pivotMotor.restoreFactoryDefaults();
		pid = pivotMotor.getPIDController();
        pid.setP(IntakeConstants.kP);
        pid.setI(IntakeConstants.kI);
        pid.setD(IntakeConstants.kD);
        pid.setIZone(IntakeConstants.kIz);
        pid.setFF(IntakeConstants.kF);
        pid.setOutputRange(IntakeConstants.kMinOutput, IntakeConstants.kMaxOutput);
        encoder.setPositionConversionFactor(IntakeConstants.gearRatio);
        encoder.setPosition(IntakeConstants.startingPosition);
	}

    public void setPos(double pos) {
        this.pos = MathUtil.clamp(pos, IntakeConstants.kMinAngle, IntakeConstants.kMaxAngle);
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void resetPivot() {
        encoder.setPosition(0);
    }

    public double getAngle(){
        return encoder.getPosition();
    }

    public double getVelocity(){
        return encoder.getVelocity();
    }

    public boolean isAtPosition(){
		if(currentPos != PositionsIntake.STORAGE2)
			return Math.abs(pos-getAngle())<.5;
		else 
			return pos<IntakeConstants.getTargetPos(PositionsIntake.STORAGE2);
	}

    @Override
    public void periodic() {
        if (isPosControl) {
            pivotMotor.getPIDController().setReference(pos, ControlType.kPosition);
        }else{
            pivotMotor.set(speed);
        }
    }

}
