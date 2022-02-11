package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.motorcontrol.PWMTalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class RollerIntake extends SubsystemBase{

    private WPI_TalonFX rTalon;

    public RollerIntake() {
        rTalon = new WPI_TalonFX(Constants.rTalonID);
        addChild("RollerTalon", rTalon);
        rTalon.setInverted(false);
    }

    public void setSpeed(double speed){
        rTalon.set(speed);  
    }

    public void stopMotors() {
        rTalon.stopMotor();
    }
}