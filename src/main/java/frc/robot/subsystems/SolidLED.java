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

public class SolidLED extends SubsystemBase {
  /**
   * Creates a new SolidLED.
   */

  public static AddressableLED led;
  public static AddressableLEDBuffer m_buffer;
  public static Color purple, gold;
  public Color[] color;
  public Color temp;
  
  public SolidLED() {
    led = new AddressableLED(1);
    m_buffer = new AddressableLEDBuffer(178);
    led.setLength(m_buffer.getLength());
    led.start();

    purple = new Color(84, 0, 84);
    gold = new Color(240, 100, 0);

    color = new Color[m_buffer.getLength()];

    for(int i = 0; i < m_buffer.getLength(); i++)
    {
      if(i % 10 >= 5)
        color[i] = gold;
      else
        color[i] = purple;
      m_buffer.setRGB(i, color[i].getRed(), color[i].getGreen(), color[i].getBlue());
    }
    
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
