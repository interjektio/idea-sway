package fi.interjektio.dev.idea.sway
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.LspServerSupportProvider
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor
import com.intellij.openapi.diagnostic.logger

// Most of this is adapted from
// https://plugins.jetbrains.com/docs/intellij/language-server-protocol.html

private const val FILE_EXTENSION = "sw";
private const val LSP_COMMAND = "forc-lsp";
private const val PRESENTABLE_NAME = "Sway"
private val LOG = logger<SwayLspServerSupportProvider>()

class SwayLspServerSupportProvider : LspServerSupportProvider {
    override fun fileOpened(project: Project, file: VirtualFile, serverStarter: LspServerSupportProvider.LspServerStarter) {
        LOG.info("fileOpened: $file ${file.extension}")
        if (file.extension == FILE_EXTENSION) {
            LOG.info("ensuring server started");
            serverStarter.ensureServerStarted(SwayLspServerDescriptor(project));
            LOG.info("done ensuring server started");
        }
    }
}

private class SwayLspServerDescriptor(project: Project) : ProjectWideLspServerDescriptor(project, PRESENTABLE_NAME) {
    override fun isSupportedFile(file: VirtualFile) = file.extension == FILE_EXTENSION
    override fun createCommandLine() = GeneralCommandLine(LSP_COMMAND)
}
