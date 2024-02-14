package frc.robot.subsystems;

import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.IntakeConstants.PositionsIntake;

public class PivotIntakeSubsystem extends SubsystemBase {
    private static PivotIntakeSubsystem pivot;

    private SparkPIDController pid;
    private CANSparkMax pivotMotor;
    private double pos;
    private RelativeEncoder encoder;
    private boolean isPosControl = false;

    private double speed;

    private PositionsIntake currentPos;

    public static PivotIntakeSubsystem getInstance() {
        if (pivot == null) pivot = new PivotIntakeSubsystem();
        return pivot;
    }

    public PivotIntakeSubsystem() {
        pivotMotor = new CANSparkMax(IntakeConstants.PivotID, MotorType.kBrushless);
        isPosControl = false;
        encoder = pivotMotor.getEncoder();
        configurePivotMotor();
    }

	private void configurePivotMotor() {
		pivotMotor.restoreFactoryDefaults();
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
        encoder.setPosition(IntakeConstants.startingPosition);
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

    public void adjustUp() {
		pos += 2;
	}

	public void adjustDown() {
		pos -= 2;
	}

    public void setCurrentPosition(PositionsIntake pos) {
		currentPos = pos;
	}

    public void savePositions() {
		switch (currentPos) {
			default:
				break;

		}
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
