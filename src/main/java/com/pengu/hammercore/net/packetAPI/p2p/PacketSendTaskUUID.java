package com.pengu.hammercore.net.packetAPI.p2p;

import java.util.UUID;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

import com.pengu.hammercore.net.HCNetwork;
import com.pengu.hammercore.net.packetAPI.IPacket;
import com.pengu.hammercore.net.packetAPI.IPacketListener;
import com.pengu.hammercore.utils.NBTUtils;

public class PacketSendTaskUUID implements IPacket, IPacketListener<PacketSendTaskUUID, IPacket>
{
	private NBTTagCompound task;
	private String sender;
	private UUID[] receivers;
	
	public PacketSendTaskUUID(ITask task, UUID... receivers)
	{
		this.task = new NBTTagCompound();
		NBTTagCompound tag = new NBTTagCompound();
		task.writeToNBT(tag);
		this.task.setTag("Data", tag);
		this.task.setString("Class", task.getClass().getName());
		
		this.sender = "?";
		this.receivers = receivers;
	}
	
	public PacketSendTaskUUID()
	{
	}
	
	@Override
	public IPacket onArrived(PacketSendTaskUUID packet, MessageContext context)
	{
		if(context.side == Side.SERVER)
		{
			packet.sender = context.getServerHandler().player.getGameProfile().getName();
			for(UUID $receiver : packet.receivers)
			{
				EntityPlayerMP receiver = context.getServerHandler().player.mcServer.getPlayerList().getPlayerByUUID($receiver);
				if(receiver != null)
					HCNetwork.manager.sendTo(packet, receiver);
			}
		}
		if(context.side == Side.CLIENT)
		{
			try
			{
				ITask task = (ITask) Class.forName(this.task.getString("Class")).newInstance();
				task.readFromNBT(this.task.getCompoundTag("Data"));
				task.execute(context);
			} catch(Throwable err)
			{
				err.printStackTrace();
			}
		}
		return null;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		nbt.setTag("Task", this.task);
		nbt.setString("Sen", sender);
		NBTUtils.writeUUIDArrayToNBT("Rec", nbt, receivers);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		this.task = nbt.getCompoundTag("Task");
		sender = nbt.getString("Sen");
		receivers = NBTUtils.readUUIDArrayFromNBT("Rec", nbt);
	}
}