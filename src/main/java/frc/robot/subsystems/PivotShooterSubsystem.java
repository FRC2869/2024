package frc.robot.subsystems;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PivotShooterSubsystem extends SubsystemBase {
    private static PivotShooterSubsystem pivot;

    
    private double position;
    private RelativeEncoder encoder;
    private boolean isMoving;
    private CANSparkFlex sparkMax;

    public static PivotShooterSubsystem getInstance() {
        if (pivot == null) pivot = new PivotShooterSubsystem();
        return pivot;
    }

    public void setSpeed(double speed) {
        sparkMax.set(speed);
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
