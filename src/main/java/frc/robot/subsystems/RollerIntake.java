package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Intake;

public class RollerIntake extends SubsystemBase{

    private PWMVictorSPX rIntake;

    public RollerIntake() {
        rIntake = new PWMVictorSPX(Intake.intakeID);
        addChild("RollerVictor", rIntake);
        rIntake.setInverted(false);
    }

    /**
     * @param speed
     */

    public void setSpeed(double speed){
        rIntake.set(speed);  
    }

    public void stopMotors() {
        rIntake.stopMotor();
    }
}