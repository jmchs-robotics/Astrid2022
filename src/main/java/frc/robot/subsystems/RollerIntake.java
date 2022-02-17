package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMTalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class RollerIntake extends SubsystemBase{

    private PWMTalonFX rTalon;

    public RollerIntake() {
        rTalon = new PWMTalonFX(8);
        addChild("RollerTalon", rTalon);
        rTalon.setInverted(false);
    }

    /**
     * @param speed
     */

    public void setSpeed(double speed){
        rTalon.set(speed);  
    }

    public void stopMotors() {
        rTalon.stopMotor();
    }
}