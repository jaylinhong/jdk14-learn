package jdk.tools.jlink.resources;

import java.util.ListResourceBundle;

public final class jlink_zh_CN extends ListResourceBundle {
    protected final Object[][] getContents() {
        return new Object[][] {
            { "err.automatic.module", "\u81EA\u52A8\u6A21\u5757\u4E0D\u80FD\u7528\u4E8E\u6765\u81EA {1} \u7684 jlink: {0}" },
            { "err.badpattern", "\u9519\u8BEF\u7684\u6A21\u5F0F {0}" },
            { "err.bom.generation", "\u672A\u80FD\u751F\u6210 bom \u6587\u4EF6: {0}" },
            { "err.cannot.read.module.info", "\u65E0\u6CD5\u4ECE {0} \u8BFB\u53D6\u6A21\u5757\u63CF\u8FF0\u7B26" },
            { "err.config.defaults", "\u914D\u7F6E\u4E2D\u7F3A\u5C11\u5C5E\u6027 {0}" },
            { "err.config.defaults.value", "\u9ED8\u8BA4\u5C5E\u6027\u4E2D\u7684\u503C\u9519\u8BEF: {0}" },
            { "err.dir.exists", "{0} \u5DF2\u5B58\u5728" },
            { "err.empty.module.path", "\u7A7A\u6A21\u5757\u8DEF\u5F84" },
            { "err.existing.image.invalid", "\u73B0\u6709\u6620\u50CF\u65E0\u6548" },
            { "err.file.error", "\u65E0\u6CD5\u8BBF\u95EE\u6587\u4EF6: {0}" },
            { "err.file.not.found", "\u627E\u4E0D\u5230\u6587\u4EF6: {0}" },
            { "err.image.must.exist", "\u6620\u50CF {0} \u4E0D\u5B58\u5728\u6216\u8005\u4E0D\u662F\u76EE\u5F55" },
            { "err.internal.error", "\u5185\u90E8\u9519\u8BEF: {0} {1} {2}" },
            { "err.invalid.arg.for.option", "{0} \u4E0D\u63A5\u53D7 \"{1}\" \u53C2\u6570" },
            { "err.jlink.version.mismatch", "jlink \u7248\u672C {0}.{1} \u4E0E\u76EE\u6807 java.base \u7248\u672C {2}.{3} \u4E0D\u5339\u914D" },
            { "err.launcher.main.class.empty", "\u542F\u52A8\u7A0B\u5E8F\u4E3B\u7C7B\u540D\u4E0D\u80FD\u4E3A\u7A7A: {0}" },
            { "err.launcher.module.name.empty", "\u542F\u52A8\u7A0B\u5E8F\u6A21\u5757\u540D\u79F0\u4E0D\u80FD\u4E3A\u7A7A: {0}" },
            { "err.launcher.value.format", "\u542F\u52A8\u7A0B\u5E8F\u503C\u5E94\u4F7F\u7528\u201C<\u547D\u4EE4>=<\u6A21\u5757>[/<\u4E3B\u7C7B>]\u201D\u683C\u5F0F: {0}" },
            { "err.missing.arg", "\u6CA1\u6709\u4E3A{0}\u6307\u5B9A\u503C" },
            { "err.mods.must.be.specified", "\u6CA1\u6709\u5C06\u4EFB\u4F55\u6A21\u5757\u6307\u5B9A\u5230{0}" },
            { "err.modulepath.must.be.specified", "\u672A\u6307\u5B9A --module-path\uFF0C\u6B64\u8FD0\u884C\u65F6\u6620\u50CF\u4E0D\u5305\u542B jmods \u76EE\u5F55\u3002" },
            { "err.not.a.module.directory", "\u76EE\u5F55 {0} \u4E0B\u4E0D\u5305\u542B module-info.class \u6587\u4EF6" },
            { "err.not.modular.format", "\u9009\u5B9A\u6A21\u5757 {0} ({1}) \u672A\u4F7F\u7528 jmod \u6216\u6A21\u5757\u5316 JAR \u683C\u5F0F" },
            { "err.option.after.class", "\u5FC5\u987B\u5728\u7C7B\u4E4B\u524D\u6307\u5B9A\u9009\u9879: {0}" },
            { "err.option.unsupported", "\u4E0D\u652F\u6301{0}: {1}" },
            { "err.orphan.arguments", "\u65E0\u6548\u7684\u53C2\u6570: {0}" },
            { "err.output.must.be.specified", "\u5FC5\u987B\u6307\u5B9A --output" },
            { "err.path.not.found", "\u627E\u4E0D\u5230\u8DEF\u5F84: {0}" },
            { "err.path.not.valid", "\u65E0\u6548\u8DEF\u5F84: {0}" },
            { "err.signing", "\u5F53\u524D\u4E0D\u652F\u6301\u5DF2\u7B7E\u540D\u6A21\u5757\u5316 JAR {0}, \u4F7F\u7528 --ignore-signing-information \u53EF\u9690\u85CF\u9519\u8BEF" },
            { "err.unknown.byte.order", "\u672A\u77E5\u7684\u5B57\u8282\u987A\u5E8F {0}" },
            { "err.unknown.option", "\u672A\u77E5\u9009\u9879: {0}" },
            { "error.prefix", "\u9519\u8BEF:" },
            { "main.command.files", "      @<\u6587\u4EF6\u540D>                           \u4ECE\u6587\u4EF6\u4E2D\u8BFB\u53D6\u9009\u9879" },
            { "main.extended.help", "\u53EF\u7528\u63D2\u4EF6\u5217\u8868:" },
            { "main.extended.help.footer", "\u5BF9\u4E8E\u9700\u8981 <\u6A21\u5F0F\u5217\u8868> \u7684\u9009\u9879, \u503C\u5C06\u4E3A\u9017\u53F7\u5206\u9694\u7684\u5143\u7D20\u5217\u8868, \u6BCF\u4E2A\u5143\u7D20\u4F7F\u7528\u4EE5\u4E0B\u683C\u5F0F\u4E4B\u4E00:\n  <glob \u6A21\u5F0F>\n  glob:<glob \u6A21\u5F0F>\n  regex:<\u6B63\u5219\u8868\u8FBE\u5F0F\u6A21\u5F0F>\n  @<\u6587\u4EF6\u540D>, \u5176\u4E2D\u201C\u6587\u4EF6\u540D\u201D\u662F\u5305\u542B\u8981\u4F7F\u7528\u7684\u6A21\u5F0F\u7684\u6587\u4EF6\u540D, \u6BCF\u884C\u4E00\u4E2A\u6A21\u5F0F\n\n" },
            { "main.msg.bug", "jlink \u4E2D\u51FA\u73B0\u5F02\u5E38\u9519\u8BEF\u3002\u5982\u679C\u5728 Java Bug Database (http://bugreport.java.com/bugreport/) \u4E2D\u6CA1\u6709\u627E\u5230\u8BE5\u9519\u8BEF, \u8BF7\u5728\u8BE5\u6570\u636E\u5E93\u4E2D\u5EFA\u7ACB Bug\u3002\u8BF7\u5728\u62A5\u544A\u4E2D\u9644\u4E0A\u60A8\u7684\u7A0B\u5E8F\u548C\u4EE5\u4E0B\u8BCA\u65AD\u4FE1\u606F\u3002\u8C22\u8C22\u3002" },
            { "main.opt.add-modules", "      --add-modules <mod>[,<mod>...]    \u9664\u4E86\u521D\u59CB\u6A21\u5757\u4E4B\u5916\u8981\u89E3\u6790\u7684\n                                        \u6839\u6A21\u5757\u3002<mod> \u8FD8\u53EF\u4EE5\u4E3A ALL-MODULE-PATH\u3002" },
            { "main.opt.bind-services", "      --bind-services                   \u94FE\u63A5\u670D\u52A1\u63D0\u4F9B\u65B9\u6A21\u5757\u53CA\u5176\n                                        \u88AB\u4F9D\u8D56\u5BF9\u8C61" },
            { "main.opt.endian", "      --endian <little|big>               \u6240\u751F\u6210 jimage\n                                          \u7684\u5B57\u8282\u987A\u5E8F (\u9ED8\u8BA4\u503C: native)" },
            { "main.opt.help", "  -h, --help, -?                        \u8F93\u51FA\u6B64\u5E2E\u52A9\u6D88\u606F" },
            { "main.opt.ignore-signing-information", "      --ignore-signing-information        \u5728\u6620\u50CF\u4E2D\u94FE\u63A5\u5DF2\u7B7E\u540D\u6A21\u5757\u5316\n                                          JAR \u7684\u60C5\u51B5\u4E0B\u9690\u85CF\u81F4\u547D\u9519\u8BEF\u3002\n                                          \u5DF2\u7B7E\u540D\u6A21\u5757\u5316 JAR \u7684\u7B7E\u540D\n                                          \u76F8\u5173\u6587\u4EF6\u5C06\u4E0D\u4F1A\u590D\u5236\u5230\n                                          \u8FD0\u884C\u65F6\u6620\u50CF\u3002" },
            { "main.opt.launcher", "      --launcher <\u540D\u79F0>=<\u6A21\u5757>[/<\u4E3B\u7C7B>]\n                                        \u4E3A\u6A21\u5757\u548C\u4E3B\u7C7B\u6DFB\u52A0\u7ED9\u5B9A\n                                        \u540D\u79F0\u7684\u542F\u52A8\u7A0B\u5E8F\u547D\u4EE4\n                                        (\u5982\u679C\u6307\u5B9A)  " },
            { "main.opt.limit-modules", "      --limit-modules <\u6A21\u5757>[,<\u6A21\u5757>...]  \u9650\u5236\u53EF\u89C2\u5BDF\u6A21\u5757\u7684\u9886\u57DF" },
            { "main.opt.module-path", "  -p, --module-path <path>              \u6A21\u5757\u8DEF\u5F84\u3002\n                                        \u5982\u679C\u672A\u6307\u5B9A\uFF0C\u5C06\u4F7F\u7528 JDK \u7684 jmods \n                                        \u76EE\u5F55\uFF08\u5982\u679C\u5B58\u5728\u8BE5\u76EE\u5F55\uFF09\u3002\u5982\u679C\u6307\u5B9A\uFF0C\n                                        \u4F46\u5B83\u4E0D\u5305\u542B java.base \u6A21\u5757\uFF0C\n                                        \u5219\u5C06\u6DFB\u52A0 JDK \u7684 jmods \u76EE\u5F55\n                                        \uFF08\u5982\u679C\u5B58\u5728\u8BE5\u76EE\u5F55\uFF09\u3002" },
            { "main.opt.output", "      --output <\u8DEF\u5F84>                     \u8F93\u51FA\u8DEF\u5F84\u7684\u4F4D\u7F6E" },
            { "main.opt.save-opts", "      --save-opts <\u6587\u4EF6\u540D>                \u5C06 jlink \u9009\u9879\u4FDD\u5B58\u5728\u6307\u5B9A\u6587\u4EF6\u4E2D" },
            { "main.opt.suggest-providers", "      --suggest-providers [<\u540D\u79F0>,...]  \u5EFA\u8BAE\u53EF\u4ECE\u6A21\u5757\u8DEF\u5F84\u4E2D\u5B9E\u73B0\n                                        \u7ED9\u5B9A\u670D\u52A1\u7C7B\u578B\u7684\u63D0\u4F9B\u65B9" },
            { "main.opt.verbose", "  -v, --verbose                         \u542F\u7528\u8BE6\u7EC6\u8DDF\u8E2A" },
            { "main.opt.version", "      --version                           \u7248\u672C\u4FE1\u606F" },
            { "main.usage", "\u7528\u6CD5: {0} <\u9009\u9879> --module-path <\u6A21\u5757\u8DEF\u5F84> --add-modules <\u6A21\u5757>[,<\u6A21\u5757>...]\n\u53EF\u80FD\u7684\u9009\u9879\u5305\u62EC:" },
            { "main.usage.summary", "\u7528\u6CD5: {0} <\u9009\u9879> --module-path <\u6A21\u5757\u8DEF\u5F84> --add-modules <\u6A21\u5757>[,<\u6A21\u5757>...]\n\u4F7F\u7528 --help \u53EF\u5217\u51FA\u53EF\u80FD\u7684\u9009\u9879" },
            { "no.suggested.providers", "\u6307\u5B9A\u4E86 --bind-services \u9009\u9879\u3002\u672A\u5EFA\u8BAE\u5176\u4ED6\u63D0\u4F9B\u65B9\u3002" },
            { "providers.header", "\u63D0\u4F9B\u65B9" },
            { "suggested.providers.header", "\u5EFA\u8BAE\u7684\u63D0\u4F9B\u65B9" },
            { "warn.invalid.arg", "\u7C7B\u540D\u65E0\u6548\u6216\u8DEF\u5F84\u540D\u4E0D\u5B58\u5728: {0}" },
            { "warn.prefix", "\u8B66\u544A:" },
            { "warn.provider.notfound", "\u627E\u4E0D\u5230\u4E3A --suggest-providers \u6307\u5B9A\u7684\u670D\u52A1\u63D0\u4F9B\u65B9: {0}" },
            { "warn.signing", "\u8B66\u544A: \u5F53\u524D\u4E0D\u652F\u6301\u5DF2\u7B7E\u540D\u6A21\u5757\u5316 JAR {0}" },
            { "warn.split.package", "\u5DF2\u5728{1} {2}\u4E2D\u5B9A\u4E49\u7A0B\u5E8F\u5305{0}" },
        };
    }
}
