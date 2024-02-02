package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PivotIntakeSubsystem extends SubsystemBase {
    private static PivotIntakeSubsystem pivot;

    private CANSparkMax sparkMax;
    private double position;
    private RelativeEncoder encoder;
    private boolean isMoving;

    public static PivotIntakeSubsystem getInstance() {
        if (pivot == null) pivot = new PivotIntakeSubsystem();
        return pivot;
    }

    public PivotIntakeSubsystem() {
        sparkMax = new CANSparkMax(0, MotorType.kBrushless);
        isMoving = false;
        encoder = sparkMax.getEncoder();
    }

    public void setPosition(double pos) {
        position = pos;
        isMoving = true;
    }

    @Override
    public void periodic() {
        if (isMoving) {
            if (encoder.getPosition() > position) {
                sparkMax.set(-.5);
            }
            else if (encoder.getPosition() < position) {
                sparkMax.set(.5);
            }
            else {
                isMoving = false;
            }
        }
    }
}
