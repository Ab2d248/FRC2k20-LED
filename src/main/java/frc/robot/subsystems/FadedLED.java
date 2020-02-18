/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;
import java.awt.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FadedLED extends SubsystemBase {
  /**
   * Creates a new FadedLED.
   */

  public static AddressableLED led;
  public static AddressableLEDBuffer m_buffer;
  public static Color color1, color2;
  public Color[] color;
  public Color temp;
  public int colorBlockLength;
  public int diffRedGradient, diffGreenGradient, diffBlueGradient, currRedGradient, currGreenGradient, currBlueGradient;
  
  public FadedLED() {
    led = new AddressableLED(1);
    m_buffer = new AddressableLEDBuffer(178);
    led.setLength(m_buffer.getLength());
    led.start();

    color1 = new Color(84, 0, 84);
    color2 = new Color(240, 100, 0);

    colorBlockLength = 5;

    diffRedGradient = (color2.getRed() - color1.getRed())/colorBlockLength;
    diffGreenGradient = (color2.getGreen() - color1.getGreen())/colorBlockLength;
    diffBlueGradient = (color2.getBlue() - color1.getBlue())/colorBlockLength;

    color = new Color[m_buffer.getLength()];
    color[0] = color1;

    for(int i = 0; i < m_buffer.getLength(); i++)
    {
      if(color[i].getRed() > 0 && color[i].getGreen() > 0 && color[i].getBlue() > 0)
        m_buffer.setRGB(i, color[i].getRed(), color[i].getGreen(), color[i].getBlue());

      if(color[i].equals(color1) || color[i].equals(color2))
      {
        diffRedGradient *= -1;
        diffGreenGradient *= -1;
        diffBlueGradient *= -1;
      }

      currRedGradient += diffRedGradient;
      currGreenGradient += diffGreenGradient;
      currBlueGradient += diffBlueGradient;

      color[i] = new Color(currRedGradient, currGreenGradient, currBlueGradient);
    }

    led.setData(m_buffer);
    
    temp = color[0];
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    temp = color[0];

    for(int i = 0; i < m_buffer.getLength()-1; i++)
    {
      color[i] = color[i+1];
      m_buffer.setRGB(i, color[i].getRed(), color[i].getGreen(), color[i].getBlue());
    }

    color[color.length-1] = temp;

    led.setData(m_buffer);
    Timer.delay(0.05);


  }
}
