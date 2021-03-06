/*
 * Copyright (c) 2012, 2019, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package jdk.incubator.jpackage.internal;

import java.io.File;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.*;

import static jdk.incubator.jpackage.internal.WindowsBundlerParam.*;

public class WinAppBundler extends AbstractImageBundler {

    private static final ResourceBundle I18N = ResourceBundle.getBundle(
            "jdk.incubator.jpackage.internal.resources.WinResources");

    static final BundlerParamInfo<File> ICON_ICO =
            new StandardBundlerParam<>(
            "icon.ico",
            File.class,
            params -> {
                File f = ICON.fetchFrom(params);
                if (f != null && !f.getName().toLowerCase().endsWith(".ico")) {
                    Log.error(MessageFormat.format(
                            I18N.getString("message.icon-not-ico"), f));
                    return null;
                }
                return f;
            },
            (s, p) -> new File(s));

    @Override
    public boolean validate(Map<String, ? super Object> params)
            throws ConfigException {
        try {
            Objects.requireNonNull(params);
            return doValidate(params);
        } catch (RuntimeException re) {
            if (re.getCause() instanceof ConfigException) {
                throw (ConfigException) re.getCause();
            } else {
                throw new ConfigException(re);
            }
        }
    }

    // to be used by chained bundlers, e.g. by EXE bundler to avoid
    // skipping validation if p.type does not include "image"
    private boolean doValidate(Map<String, ? super Object> p)
            throws ConfigException {

        imageBundleValidation(p);
        return true;
    }

    public boolean bundle(Map<String, ? super Object> p, File outputDirectory)
            throws PackagerException {
        return doBundle(p, outputDirectory, false) != null;
    }

    File doBundle(Map<String, ? super Object> p, File outputDirectory,
            boolean dependentTask) throws PackagerException {
        if (StandardBundlerParam.isRuntimeInstaller(p)) {
            return PREDEFINED_RUNTIME_IMAGE.fetchFrom(p);
        } else {
            return doAppBundle(p, outputDirectory, dependentTask);
        }
    }

    File doAppBundle(Map<String, ? super Object> p, File outputDirectory,
            boolean dependentTask) throws PackagerException {
        try {
            File rootDirectory = createRoot(p, outputDirectory, dependentTask,
                    APP_NAME.fetchFrom(p));
            AbstractAppImageBuilder appBuilder =
                    new WindowsAppImageBuilder(p, outputDirectory.toPath());
            if (PREDEFINED_RUNTIME_IMAGE.fetchFrom(p) == null ) {
                JLinkBundlerHelper.execute(p, appBuilder);
            } else {
                StandardBundlerParam.copyPredefinedRuntimeImage(p, appBuilder);
            }
            if (!dependentTask) {
                Log.verbose(MessageFormat.format(
                        I18N.getString("message.result-dir"),
                        outputDirectory.getAbsolutePath()));
            }
            return rootDirectory;
        } catch (PackagerException pe) {
            throw pe;
        } catch (Exception e) {
            Log.verbose(e);
            throw new PackagerException(e);
        }
    }

    @Override
    public String getName() {
        return I18N.getString("app.bundler.name");
    }

    @Override
    public String getID() {
        return "windows.app";
    }

    @Override
    public String getBundleType() {
        return "IMAGE";
    }

    @Override
    public File execute(Map<String, ? super Object> params,
            File outputParentDir) throws PackagerException {
        return doBundle(params, outputParentDir, false);
    }

    @Override
    public boolean supported(boolean platformInstaller) {
        return true;
    }

    @Override
    public boolean isDefault() {
        return false;
    }

}
