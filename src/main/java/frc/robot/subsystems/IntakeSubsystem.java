package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.Constants.Intake;

public class IntakeSubsystem extends SubsystemBase{

    private WPI_VictorSPX rIntake;

    public IntakeSubsystem() {
        rIntake = RobotMap.rollerMotor;
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
        rIntake.set(0);
    }

    public double deadband(double input){
        if (Math.abs(input) <= 0.05) {
            return 0;
        }
        return input;
    }    
}