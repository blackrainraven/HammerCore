package com.pengu.hammercore.net.utils;

import java.nio.ByteBuffer;

import com.pengu.hammercore.utils.NBTUtils;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;

public class NetPropertyNumber<T extends Number> extends NetPropertyAbstract<T>
{
	public NetPropertyNumber(IPropertyChangeHandler handler)
	{
		super(handler);
	}
	
	public NetPropertyNumber(IPropertyChangeHandler handler, T initialValue)
	{
		super(handler, initialValue);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt)
	{
		NBTUtils.writeNumberToNBT("val", nbt, value);
		return nbt;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		value = (T) NBTUtils.readNumberFromNBT("val", nbt);
	}
}