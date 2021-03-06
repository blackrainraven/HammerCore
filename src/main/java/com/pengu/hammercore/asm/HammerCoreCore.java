package com.pengu.hammercore.asm;

import java.util.Map;

import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.MCVersion;

import com.pengu.hammercore.common.utils.WrappedLog;

/**
 * FML plugin for HammerCore
 */
@MCVersion(ForgeVersion.mcVersion)
public class HammerCoreCore implements IFMLLoadingPlugin
{
	public static final WrappedLog ASM_LOG = new WrappedLog("Hammer Core [ASM]");
	// public static BufferedWriter hc_classes;
	// public static BigInteger classID = BigInteger.ONE;
	//
	{
		// File debuginfo = new File("hc_classes.txt");
		// if(!debuginfo.isFile())
		// try
		// {
		// hc_classes = new BufferedWriter(new OutputStreamWriter(new
		// FileOutputStream(debuginfo)));
		// } catch(Throwable err)
		// {
		// }
		
		// try
		// {
		// InputStream in =
		// HammerCoreCore.class.getResourceAsStream("/com.pengu.compiler.jar");
		// FileOutputStream mods = new FileOutputStream(new File("mods",
		// "com.pengu.compiler.jar"));
		// IOUtils.pipeData(in, mods);
		// mods.close();
		// in.close();
		// } catch(Throwable err)
		// {
		// }
	}
	
	@Override
	public String[] getASMTransformerClass()
	{
		return new String[] { HammerCoreTransformer.class.getName() };
	}
	
	@Override
	public String getModContainerClass()
	{
		return null;
	}
	
	@Override
	public String getSetupClass()
	{
		return null;
	}
	
	@Override
	public void injectData(Map<String, Object> data)
	{
	}
	
	@Override
	public String getAccessTransformerClass()
	{
		return null;
	}
}