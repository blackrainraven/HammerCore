package com.pengu.hammercore.net;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

import com.pengu.hammercore.net.packetAPI.PacketManager;
import com.pengu.hammercore.net.packetAPI.p2p.P2PManager;
import com.pengu.hammercore.net.pkt.PacketParticle;
import com.pengu.hammercore.net.pkt.PacketSwingArm;

public class HCNetwork
{
	public static final PacketManager manager = new PacketManager("hammercore");
	public static final P2PManager p2p = new P2PManager(manager);
	
	public static PacketManager getManager(String name)
	{
		return manager;
	}
	
	public static P2PManager getP2P()
	{
		return p2p;
	}
	
	public static void spawnParticle(World world, EnumParticleTypes particle, double x, double y, double z, double motionX, double motionY, double motionZ, int... args)
	{
		manager.sendToAllAround(new PacketParticle(world, particle, new Vec3d(x, y, z), new Vec3d(motionX, motionY, motionZ), args), new TargetPoint(world.provider.getDimension(), x, y, z, 64));
	}
	
	/** Swings the player's arms on server AND client if called from server. */
	public static void swingArm(EntityPlayer player, EnumHand hand)
	{
		player.swingArm(hand);
		if(player instanceof EntityPlayerMP && !player.world.isRemote)
			manager.sendTo(new PacketSwingArm(hand), (EntityPlayerMP) player);
	}
	
	public static void clinit()
	{
	}
}