package hats.common.core;

import java.io.File;
import java.util.Iterator;
import java.util.Map.Entry;

import hats.common.Hats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class ConnectionHandler 
	implements IConnectionHandler, IPlayerTracker 
{

	@Override
	public void connectionOpened(NetHandler netClientHandler, String server, int port, INetworkManager manager) //client: remove server
	{
		onClientConnected();
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, MinecraftServer server, INetworkManager manager) //client: local server
	{
		onClientConnected();
	}
	
	public void onClientConnected()
	{
		Hats.proxy.tickHandlerClient.serverHasMod = false;
		
		Hats.proxy.tickHandlerClient.availableHats.clear();
		
		Iterator<Entry<File, String>> ite = Hats.proxy.hatNames.entrySet().iterator();
		while(ite.hasNext())
		{
			Entry<File, String> e = ite.next();
			Hats.proxy.tickHandlerClient.availableHats.add(e.getValue());
		}

	}

	@Override
	public String connectionReceived(NetLoginHandler netHandler, INetworkManager manager) //server
	{
		return null;
	}

	@Override
	public void clientLoggedIn(NetHandler clientHandler, INetworkManager manager, Packet1Login login) //client
	{
	}

	@Override
	public void playerLoggedIn(Player player, NetHandler netHandler, INetworkManager manager) //server
	{
		
	}

	@Override
	public void connectionClosed(INetworkManager manager) //both 
	{
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
		{
			Hats.proxy.tickHandlerClient.hats.clear();
		}
	}


	//IPlayerTracker area
	
	@Override
	public void onPlayerLogin(EntityPlayer player) 
	{
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) 
	{
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) 
	{
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player) 
	{

	}

}
