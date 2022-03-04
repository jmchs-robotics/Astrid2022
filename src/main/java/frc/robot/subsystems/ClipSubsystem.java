package frc.robot.subsystems;

import frc.robot.RobotMap;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 *
 */
public class ClipSubsystem extends SubsystemBase {

    private DoubleSolenoid strongPiston;
    private DoubleSolenoid weakPiston;
        
    /**
    *
    */
    public ClipSubsystem() {

        strongPiston = RobotMap.strongPiston;
        addChild("strongPiston", strongPiston);
        weakPiston = RobotMap.weakPiston;
        addChild("weakPiston", weakPiston);  

    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    public void setWeakSolenoid (Value val) {
        weakPiston.set(val);
    }

    public void setStrongSolenoid (Value val) {
        strongPiston.set(val);
    }

    public void setBothSolenoids (Value val) {
        weakPiston.set(val);
        strongPiston.set(val);
    }

    public String getPistonValue() {
        return "weak: " + weakPiston.get() + "   strong: " + strongPiston.get();
    }

}
 
