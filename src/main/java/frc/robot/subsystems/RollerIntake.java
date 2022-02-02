package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class RollerIntake extends SubsystemBase{

    private Solenoid rSolenoid;
    private PWMTalonFX rTalon;

    public RollerIntake() {
        rSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 0);
        addChild("RollerMotor", rSolenoid);

        rTalon = new PWMTalonFX(0);
        addChild("RollerTalon", rTalon);
        rTalon.setInverted(false);
    }

    public void setSpeed(double speed){
        rTalon.set(speed);  
    }

    public void stopMotors() {
        rTalon.stopMotor();
    }
     public void toggleSolenoids() {
         rSolenoid.toggle();
     }
}