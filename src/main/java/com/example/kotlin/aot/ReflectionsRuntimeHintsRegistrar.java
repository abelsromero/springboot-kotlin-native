package com.example.kotlin.aot;

import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

public class ReflectionsRuntimeHintsRegistrar implements RuntimeHintsRegistrar {

	private static final Logger logger = LoggerFactory.getLogger(ReflectionsRuntimeHintsRegistrar.class);
	public final MemberCategory[] allCategories = MemberCategory.values();


	@Override
	public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
		/**
		 * This is an example, full OpenAPI processing in Native requires extra configurations.
		 */

		final var swaggerModels = reflectTypes("io.swagger.models");
		register(hints, swaggerModels);

		final var swaggerV3Models = reflectTypes("io.swagger.v3.oas.models");
		register(hints, swaggerV3Models);
	}

	private void register(RuntimeHints hints, Set<Class<?>> types) {
		for (Class<?> type : types) {
			if (type.getCanonicalName() != null) {
				logger.info("Registering type: " + type.getCanonicalName());
				hints.reflection().registerType(type, allCategories);
			}
		}
	}

	private Set<Class<?>> reflectTypes(String packageName) {
		logger.info("Scanning with reflection " + packageName);
		Reflections reflections = buildReflections(packageName);

		var types = reflections.getSubTypesOf(Object.class);
		logger.info("Found " + types.size());
		return types;
	}

	private Reflections buildReflections(String packageName) {
		return new Reflections(new ConfigurationBuilder()
				.setScanners(new SubTypesScanner(false), new ResourcesScanner())
				.addUrls(ClasspathHelper.forJavaClassPath())
				.filterInputsBy(new FilterBuilder().includePackage(packageName)));
	}
}
