package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final class Drive {
        //Drivetrain Motor IDs
        public static final int left1ID = 3;
        public static final int left2ID = 5;
        public static final int right1ID = 2;
        public static final int right2ID = 4;

        public static final double kP_turn = 0.1;
        public static final double kI_turn = 0;
	    public static final double kD_turn = 0;

        public static final double kP_gyroTurn = 0;

        public static final double kP_forward = 0;
	    public static final double KI_forward = 0;
        public static final double kD_forward = 0;
        
        public static final double kEncoderTicksPerInch = 0;
    }

    public static final class Hook {
        //Hook Motor IDs
        public static final int leftHookID = 0;
        public static final int rightHookID = 1;

        public static final double minHookPos = 0; 
    }
        
    public static final class Clip {
        //Pneumatic Channel IDs 
        public static final int leftForwardChannel = 4;
        public static final int leftReverseChannel = 5;
        public static final int rightForwardChannel = 6;
        public static final int rightReverseChannel = 7;
    }
    
 

}