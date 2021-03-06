package com.pengu.hammercore.net.pkt;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import com.pengu.hammercore.HammerCore;
import com.pengu.hammercore.net.packetAPI.IPacket;
import com.pengu.hammercore.net.packetAPI.IPacketListener;

public class PacketSpawnSlowZap implements IPacket, IPacketListener<PacketSpawnSlowZap, IPacket>
{
	public int world, color, maxTicks;
	public float ampl;
	public Vec3d start, end;
	
	@Override
	public IPacket onArrived(PacketSpawnSlowZap packet, MessageContext context)
	{
		HammerCore.particleProxy.spawnSlowZap(packet.world, packet.start, packet.end, packet.color, packet.maxTicks, packet.ampl);
		return null;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		nbt.setDouble("sx", start.x);
		nbt.setDouble("sy", start.y);
		nbt.setDouble("sz", start.z);
		nbt.setDouble("ex", end.x);
		nbt.setDouble("ey", end.y);
		nbt.setDouble("ez", end.z);
		nbt.setInteger("dm", world);
		nbt.setInteger("cl", color);
		nbt.setInteger("mt", maxTicks);
		nbt.setFloat("am", ampl);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		start = new Vec3d(nbt.getDouble("sx"), nbt.getDouble("sy"), nbt.getDouble("sz"));
		end = new Vec3d(nbt.getDouble("ex"), nbt.getDouble("ey"), nbt.getDouble("ez"));
		world = nbt.getInteger("dm");
		color = nbt.getInteger("cl");
		maxTicks = nbt.getInteger("mt");
		ampl = nbt.getFloat("am");
	}
}