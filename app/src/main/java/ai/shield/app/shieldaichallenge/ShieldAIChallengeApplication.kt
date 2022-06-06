package ai.shield.app.shieldaichallenge

import ai.shield.app.shieldaichallenge.di.applicationModule
import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.util.DebugLogger
import logcat.AndroidLogcatLogger
import logcat.LogPriority
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class ShieldAIChallengeApplication : Application(), ImageLoaderFactory {
    override fun onCreate() {
        super.onCreate()
        // Log all priorities in debug builds, no-op in release builds.
        AndroidLogcatLogger.installOnDebuggableApp(this, minPriority = LogPriority.VERBOSE)

        // initializes the Koin DI system
        startKoin {
            androidContext(this@ShieldAIChallengeApplication)
            modules(applicationModule)
        }
    }

    // Initializes the Coil image loader
    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .crossfade(true)
            .build()
    }
}