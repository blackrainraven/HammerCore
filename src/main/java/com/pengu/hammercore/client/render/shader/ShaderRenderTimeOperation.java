package com.pengu.hammercore.client.render.shader;

import java.util.HashMap;

import net.minecraft.client.Minecraft;

import org.lwjgl.opengl.ARBShaderObjects;

public class ShaderRenderTimeOperation implements IShaderOperation
{
	public static final int operationID = HCShaderPipeline.registerOperation();
	private final HashMap<ShaderProgram, Float> shaderRenderTimeCache = new HashMap<>();
	
	@Override
	public boolean load(ShaderProgram program)
	{
		return true;
	}
	
	@Override
	public void operate(ShaderProgram program)
	{
		float renderTime = (float) Minecraft.getSystemTime();
		if(renderTime != shaderRenderTimeCache.get(program))
		{
			int location = program.getAttribLoc("time");
			ARBShaderObjects.glUniform1fARB(location, renderTime);
			shaderRenderTimeCache.put(program, renderTime);
		}
	}
	
	@Override
	public int operationID()
	{
		return operationID;
	}
}