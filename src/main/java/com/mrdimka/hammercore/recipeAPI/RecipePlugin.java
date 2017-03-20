package com.mrdimka.hammercore.recipeAPI;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Targets class to be registered to MinecraftForge EVENT_BUS on PreInit
 **/
@Target(ElementType.TYPE)
public @interface RecipePlugin {}