package io.github.pfwikis.bots.common.api;

import java.nio.file.Path;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import io.github.fastily.jwiki.core.NS;
import io.github.fastily.jwiki.dwrap.Contrib;
import io.github.fastily.jwiki.dwrap.ImageInfo;
import io.github.fastily.jwiki.dwrap.LogEntry;
import io.github.fastily.jwiki.dwrap.PageSection;
import io.github.fastily.jwiki.dwrap.ProtectedTitleEntry;
import io.github.fastily.jwiki.dwrap.RCEntry;
import io.github.fastily.jwiki.dwrap.Revision;
import io.github.fastily.jwiki.util.Tuple;
import okhttp3.HttpUrl;
import okhttp3.Response;


/*drop in replacement of jwiki Wiki class*/
public interface MWApi {

	/**
	 * Performs a basic GET action on this Wiki. Use this to implement custom or non-standard API calls.
	 * 
	 * @param action The action to perform.
	 * @param params Each parameter and its corresponding value. For example, the parameters, {@code &amp;foo=bar&amp;baz=blah}, should be passed in as {{@code "foo", "bar", "baz", "blah"}}.
	 *           URL-encoding will be applied automatically.
	 * @return The Response from the server, or null on error.
	 */
	Response basicGET(String action, String... params);

	/**
	 * Performs a basic POST action on this Wiki. Use this to implement custom or non-standard API calls.
	 * 
	 * @param action The action to perform.
	 * @param form The form data to post. This will be automatically URL-encoded.
	 * @return The Response from the server, or null on error.
	 */
	Response basicPOST(String action, HashMap<String, String> form);

	/**
	 * Check if a title in specified namespace and convert it if it is not.
	 * 
	 * @param title The title to check
	 * @param ns The namespace to convert the title to.
	 * @return The same title if it is in {@code ns}, or the converted title.
	 */
	String convertIfNotInNS(String title, NS ns);

	/**
	 * Filters pages by namespace. Only pages with a namespace in {@code ns} are selected.
	 * 
	 * @param pages Titles to filter
	 * @param ns Pages in this/these namespace(s) will be returned.
	 * @return Titles belonging to a NS in {@code ns}
	 */
	ArrayList<String> filterByNS(ArrayList<String> pages, NS... ns);

	/**
	 * Takes a Namespace prefix and gets a NS representation of it. PRECONDITION: the prefix must be a valid namespace prefix. WARNING: This method is CASE-SENSITIVE, so be sure to spell and capitalize
	 * the prefix <b>exactly</b> as it would appear on-wiki.
	 * 
	 * @param prefix The prefix to use, without the ":".
	 * @return An NS representation of the prefix.
	 */
	NS getNS(String prefix);

	/**
	 * Strip the namespace from a title.
	 * 
	 * @param title The title to strip the namespace from
	 * @return The title without a namespace
	 */
	String nss(String title);

	/**
	 * Strips the namespaces from a Collection of titles.
	 * 
	 * @param l The Collection of titles to strip namespaces from
	 * @return A List where each of the titles does not have a namespace.
	 */
	ArrayList<String> nss(Collection<String> l);

	/**
	 * Get the talk page of {@code title}.
	 * 
	 * @param title The title to get a talk page for.
	 * @return The talk page of {@code title}, or null if {@code title} is a special page or is already a talk page.
	 */
	String talkPageOf(String title);

	/**
	 * Get the name of a page belonging to a talk page ({@code title}).
	 * 
	 * @param title The talk page whose content page will be determined.
	 * @return The title of the content page associated with the specified talk page, or null if {@code title} is a special page or is already a content page.
	 */
	String talkPageBelongsTo(String title);

	/**
	 * Gets the namespace, in NS form, of a title. No namespace or an invalid namespace is assumed to be part of Main.
	 * 
	 * @param title The title to get an NS for.
	 * @return The title's NS.
	 */
	NS whichNS(String title);

	/**
	 * Gets this Wiki's logged in user.
	 * 
	 * @return The user who is logged in, or null if not logged in.
	 */
	String whoami();

	/**
	 * Gets a String representation of this Wiki, in the format {@code [username @ domain]}
	 */
	String toString();

	/**
	 * Appends text to a page. If {@code title} does not exist, then create the page normally with {@code text}
	 * 
	 * @param title The title to edit.
	 * @param add The text to append
	 * @param reason The reason to use.
	 * @param top Set to true to prepend text. False will append text.
	 * @return True if we were successful.
	 */
	boolean addText(String title, String add, String reason, boolean top);

	/**
	 * Edit a page, and check if the request actually went through.
	 * 
	 * @param title The title to use
	 * @param text The text to use
	 * @param reason The edit summary to use
	 * 
	 * @return True if the operation was successful.
	 */
	boolean edit(String title, String text, String reason);

	/**
	 * Deletes a page. You must have admin rights or this won't work.
	 * 
	 * @param title Title to delete
	 * @param reason The reason to use
	 * @return True if the operation was successful.
	 */
	boolean delete(String title, String reason);

	/**
	 * Move a page.
	 * 
	 * @param title The original title to move
	 * @param newTitle The new title to move the old page to
	 * @param moveTalk Flag indicating if {@code title}'s talk page (assuming it exists) should be moved. Optional, set false to disable.
	 * @param moveSubpages Flag indicating if {@code title}'s subpages should also be moved. Requires admin/pagemover rights, otherwise this does nothing. Optional, set false to disable.
	 * @param supressRedirect Flag indicating if a redirect to {@code newTitle} should be automatically generated at {@code title}. Requires admin/pagemover rights, otherwise this does nothing.
	 *           Optional, set false to disable.
	 * @param reason The edit summary to use
	 * @return True if the operation succeeded
	 */
	boolean move(String title, String newTitle, boolean moveTalk, boolean moveSubpages, boolean supressRedirect,
			String reason);

	/**
	 * Purges page caches.
	 * 
	 * @param titles The titles to purge.
	 */
	void purge(String... titles);

	/**
	 * Removes text from a page. Does nothing if the replacement requested wouldn't change any text on wiki (method still returns true however).
	 * 
	 * @param title The title to perform the replacement at.
	 * @param regex A regex matching the text to remove.
	 * @param reason The edit summary.
	 * @return True if we were successful.
	 */
	boolean replaceText(String title, String regex, String reason);

	/**
	 * Replaces text on a page. Does nothing if the replacement requested wouldn't change any text on wiki (method still returns true however).
	 * 
	 * @param title The title to perform replacement on.
	 * @param regex The regex matching the text to replace.
	 * @param replacement The replacing text.
	 * @param reason The edit summary.
	 * @return True if were were successful.
	 */
	boolean replaceText(String title, String regex, String replacement, String reason);

	/**
	 * Undelete a page. You must have admin rights on the wiki you are trying to perform this task on, otherwise it won't go through.
	 * 
	 * @param title The title to undelete
	 * @param reason The reason to use
	 * @return True if we successfully undeleted the page.
	 */
	boolean undelete(String title, String reason);

	/**
	 * Upload a media file.
	 * 
	 * @param p The file to use
	 * @param title The title to upload to. Must include "File:" prefix.
	 * @param text The text to put on the file description page
	 * @param reason The edit summary
	 * @return True if we were successful.
	 */
	boolean upload(Path p, String title, String text, String reason);

	/**
	 * Upload a file by URL. The URL must be on the upload by url whitelist for the target Wiki or this method will automatically fail.
	 * 
	 * @param url The URL the target file is located at.
	 * @param title The title to upload to.
	 * @param desc The text to put on the file description page
	 * @param summary The edit summary
	 * @return True if the upload was successful.
	 */
	boolean uploadByUrl(HttpUrl url, String title, String desc, String summary);

	/**
	 * Get a list of pages from the Wiki.
	 * 
	 * @param prefix Only return titles starting with this prefix. DO NOT include a namespace prefix (e.g. {@code File:}). Optional param - set null to disable
	 * @param redirectsOnly Set true to get redirects only.
	 * @param protectedOnly Set true to get protected pages only.
	 * @param cap The max number of titles to return. Optional param - set {@code -1} to get all pages.
	 * @param ns The namespace to filter by. Optional param - set null to disable
	 * @return A list of titles on this Wiki, as specified.
	 */
	ArrayList<String> allPages(String prefix, boolean redirectsOnly, boolean protectedOnly, int cap, NS ns);

	/**
	 * Checks if a title exists.
	 * 
	 * @param title The title to query.
	 * @return True if the title exists.
	 */
	boolean exists(String title);

	/**
	 * Gets a list of pages linking to a file.
	 * 
	 * @param title The title to query. PRECONDITION: This must be a valid file name prefixed with the "File:" prefix, or you will get strange results.
	 * @return A list of pages linking to the file.
	 */
	ArrayList<String> fileUsage(String title);

	/**
	 * Gets a list of file extensions for the types of files which can be uploaded to this Wiki. WARNING: this method is not cached so save the result.
	 * 
	 * @return A list of file extensions for files which can be uploaded to this Wiki.
	 */
	ArrayList<String> getAllowedFileExts();

	/**
	 * Get the categories of a page.
	 * 
	 * @param title The title to get categories of.
	 * @return A list of categories, or the empty list if something went wrong.
	 */
	ArrayList<String> getCategoriesOnPage(String title);

	/**
	 * Get a limited number of titles in a category.
	 * 
	 * @param title The category to query, including the "Category:" prefix.
	 * @param ns Namespace filter. Any title not in the specified namespace(s) will be ignored. Leave blank to select all namespaces. CAVEAT: skipped items are counted against {@code cap}.
	 * @return The list of titles, as specified, in the category.
	 */
	ArrayList<String> getCategoryMembers(String title, NS... ns);

	/**
	 * Gets the number of elements contained in a category.
	 * 
	 * @param title The title to query. PRECONDITION: Title *must* begin with the "Category:" prefix
	 * @return The number of elements in the category. Value returned will be -1 if Category entered was empty <b>and</b> non-existent.
	 */
	int getCategorySize(String title);

	/**
	 * Gets the contributions of a user.
	 * 
	 * @param user The user to get contribs for, without the "User:" prefix.
	 * @param cap The maximum number of results to return. Optional, disable with -1 (<b>caveat</b>: this will get *all* of a user's contributions)
	 * @param olderFirst Set to true to enumerate from older → newer revisions
	 * @param createdOnly Filter returned titles for instances where the contribution was a page creation. Optional, set false to disable.
	 * @param ns Restrict titles returned to the specified Namespace(s). Optional, leave blank to select all namespaces.
	 * @return A list of contributions.
	 */
	ArrayList<Contrib> getContribs(String user, int cap, boolean olderFirst, boolean createdOnly, NS... ns);

	/**
	 * List duplicates of a file.
	 * 
	 * @param title The title to query. PRECONDITION: You MUST include the namespace prefix (e.g. "File:")
	 * @param localOnly Set to true to restrict results to <span style="font-weight:bold;">local</span> duplicates only.
	 * @return Duplicates of this file.
	 */
	ArrayList<String> getDuplicatesOf(String title, boolean localOnly);

	/**
	 * Gets a list of external URLs on a page.
	 * 
	 * @param title The title to query
	 * @return A List of external links found on the page.
	 */
	ArrayList<String> getExternalLinks(String title);

	/**
	 * Gets information about a File's revisions. Does not fill the thumbnail param of ImageInfo.
	 * 
	 * @param title The title of the file to use (must be in the file namespace and exist, else return null)
	 * @return A list of ImageInfo objects, one for each revision. The order is newer -&gt; older.
	 */
	ArrayList<ImageInfo> getImageInfo(String title);

	/**
	 * Gets titles of images linked on a page.
	 * 
	 * @param title The title to query
	 * @return The images found on <code>title</code>
	 */
	ArrayList<String> getImagesOnPage(String title);

	/**
	 * Gets the username of the editor who last edited a page.
	 * 
	 * @param title The title to query
	 * @return The most recent editor of {@code title} (excluding {@code User:} prefix) or null on error.
	 */
	String getLastEditor(String title);

	/**
	 * Gets wiki links on a page.
	 * 
	 * @param title The title to query
	 * @param ns Namespaces to include-only. Optional, leave blank to select all namespaces.
	 * @return The list of wiki links on the page.
	 */
	ArrayList<String> getLinksOnPage(String title, NS... ns);

	/**
	 * Gets all existing or non-existing wiki links on a page.
	 * 
	 * @param exists Fetch mode. Set true to get existing pages and false to get missing/non-existent pages.
	 * @param title The title to query
	 * @param ns Namespaces to include-only. Optional, leave blank to select all namespaces.
	 * @return The list of existing links on {@code title}
	 */
	ArrayList<String> getLinksOnPage(boolean exists, String title, NS... ns);

	/**
	 * List log events. Order is newer -&gt; older.
	 * 
	 * @param title The title to fetch logs for. Optional - set null to disable.
	 * @param user The performing user to filter log entries by. Optional - set null to disable
	 * @param type The type of log to get (e.g. delete, upload, patrol). Optional - set null to disable
	 * @param cap Limits the number of entries returned from this log. Optional - set -1 to disable
	 * @return The log entries.
	 */
	ArrayList<LogEntry> getLogs(String title, String user, String type, int cap);

	/**
	 * Gets the first editor (creator) of a page. Specifically, get the author of the first revision of {@code title}.
	 * 
	 * @param title The title to query
	 * @return The page creator (excluding {@code User:} prefix) or null on error.
	 */
	String getPageCreator(String title);

	/**
	 * Gets the text of a page.
	 * 
	 * @param title The title to query
	 * @return The text of the page, or an empty string if the page is non-existent/something went wrong.
	 */
	String getPageText(String title);

	/**
	 * Fetches protected titles (create-protected) on the Wiki.
	 * 
	 * @param limit The maximum number of returned entries. Set -1 to disable.
	 * @param olderFirst Set to true to get older entries first.
	 * @param ns Namespace filter, limits returned titles to these namespaces. Optional param - leave blank to disable.
	 * @return An ArrayList of protected titles.
	 */
	ArrayList<ProtectedTitleEntry> getProtectedTitles(int limit, boolean olderFirst, NS... ns);

	/**
	 * Gets a list of random pages.
	 * 
	 * @param limit The number of titles to retrieve. PRECONDITION: {@code limit} cannot be a negative number.
	 * @param ns Returned titles will be in these namespaces. Optional param - leave blank to disable.
	 * @return A list of random titles on this Wiki.
	 */
	ArrayList<String> getRandomPages(int limit, NS... ns);

	/**
	 * Gets a specified number of Recent Changes in between two timestamps. WARNING: if you use both {@code start} and {@code end}, then {@code start} MUST be earlier than {@code end}. If you set both
	 * {@code start} and {@code end} to null, then the default behavior is to fetch the last 30 seconds of recent changes.
	 * 
	 * @param start The Instant to start enumerating from. Can be used without {@code end}. Optional param - set null to disable.
	 * @param end The Instant to stop enumerating at. {@code start} must be set, otherwise this will be ignored. Optional param - set null to disable.
	 * @return A list Recent Changes where return order is newer -&gt; Older
	 */
	ArrayList<RCEntry> getRecentChanges(Instant start, Instant end);

	/**
	 * Gets the revisions of a page.
	 * 
	 * @param title The title to query
	 * @param cap The maximum number of results to return. Optional param: set to any number zero or less to disable.
	 * @param olderFirst Set to true to enumerate from older → newer revisions
	 * @param start The instant to start enumerating from. Start date must occur before end date. Optional param - set null to disable.
	 * @param end The instant to stop enumerating at. Optional param - set null to disable.
	 * @return A list of page revisions
	 */
	ArrayList<Revision> getRevisions(String title, int cap, boolean olderFirst, Instant start, Instant end);

	/**
	 * Gets the shared (non-local) duplicates of a file. PRECONDITION: The Wiki this query is run against has the <a href="https://www.mediawiki.org/wiki/Extension:GlobalUsage">GlobalUsage</a>
	 * extension installed.
	 * 
	 * @param title The title of the file to query
	 * @return An ArrayList containing shared duplicates of the file
	 */
	ArrayList<String> getSharedDuplicatesOf(String title);

	/**
	 * Gets templates transcluded on a page.
	 * 
	 * @param title The title to query.
	 * @return The templates transcluded on <code>title</code>
	 */
	ArrayList<String> getTemplatesOnPage(String title);

	/**
	 * Gets a text extract (the lead paragraph) of a page.
	 * 
	 * @param title The title to get a text extract for.
	 * @return The text extract. Null if {@code title} does not exist or is a special page.
	 */
	String getTextExtract(String title);

	/**
	 * Get a user's uploads.
	 * 
	 * @param user The username, without the "User:" prefix. PRECONDITION: <code>user</code> must be a valid username.
	 * @return This user's uploads
	 */
	ArrayList<String> getUserUploads(String user);

	/**
	 * Gets the global usage of a file. PRECONDITION: GlobalUsage must be installed on the target Wiki.
	 * 
	 * @param title The title to query. Must start with <code>File:</code> prefix.
	 * @return A HashMap with the global usage of this file; each element is of the form <code>[ title : wiki ]</code>.
	 */
	ArrayList<Tuple<String, String>> globalUsage(String title);

	/**
	 * Gets the list of usergroups (rights) a user belongs to. Sample groups: sysop, user, autoconfirmed, editor.
	 * 
	 * @param user The user to get rights information for. Do not include "User:" prefix.
	 * @return The usergroups {@code user} belongs to, or null if {@code user} is an IP or non-existent user.
	 */
	ArrayList<String> listUserRights(String user);

	/**
	 * Performs a prefix search using the specified prefix and namespace, and returns the best matching titles.
	 * 
	 * @param namespace The namespace to filter by (inclusive)
	 * @param prefix Get all titles in the specified namespace, that start with this String. To select subpages only, append a {@code /} to the end of this parameter.
	 * @return The list of titles starting with the specified prefix
	 */
	ArrayList<String> prefixIndex(NS namespace, String prefix);

	/**
	 * Queries a special page.
	 * 
	 * @param title The special page to query, without the {@code Special:} prefix. CAVEAT: this is CASE-sensitive, so be sure to use the exact title (e.g. {@code UnusedFiles},
	 *           {@code BrokenRedirects}). For a full list of titles, see <a href="https://www.mediawiki.org/w/api.php?action=help&modules=query+querypage">the official documentation</a>.
	 * @param cap The maximum number of elements to return. Use {@code -1} to get everything, but be careful because some pages can have 10k+ entries.
	 * @return A List of titles returned by this special page.
	 */
	ArrayList<String> querySpecialPage(String title, int cap);

	/**
	 * Attempts to resolve title redirects on a Wiki.
	 * 
	 * @param title The title to attempt resolution at.
	 * @return The resolved title, or the original title if it was not a redirect.
	 */
	String resolveRedirect(String title);

	/**
	 * Performs a search on the Wiki.
	 * 
	 * @param query The query string to search the Wiki with.
	 * @param limit The maximum number of entries to return. Optional, specify {@code -1} to disable (not recommended if your wiki is big).
	 * @param ns Limit search to these namespaces. Optional, leave blank to disable. The default behavior is to search all namespaces.
	 * @return A List of titles found by the search.
	 */
	ArrayList<String> search(String query, int limit, NS... ns);

	/**
	 * Splits the text of a page by header.
	 * 
	 * @param title The title to query
	 * @return An ArrayList where each section (in order) is contained in a PageSection object.
	 */
	ArrayList<PageSection> splitPageByHeader(String title);

	/**
	 * Gets a list of links or redirects to a page.
	 * 
	 * @param title The title to query
	 * @param redirects Set to true to get redirects only. Set to false to filter out all redirects.
	 * @return A list of links or redirects to this page.
	 */
	ArrayList<String> whatLinksHere(String title, boolean redirects);

	/**
	 * Gets a list of direct links to a page. CAVEAT: This does not get any pages linking to a redirect pointing to this page; in order to do this you will first need to obtain a list of redirects to
	 * the target, and then call <code>whatLinksHere()</code> on each of those redirects.
	 * 
	 * @param title The title to query
	 * @return A list of links to this page.
	 */
	ArrayList<String> whatLinksHere(String title);

	/**
	 * Gets a list of pages transcluding a template.
	 * 
	 * @param title The title to query. You *must* include the namespace prefix (e.g. "Template:") or you will get strange results.
	 * @param ns Only return results from this/these namespace(s). Optional param: leave blank to disable.
	 * @return The pages transcluding <code>title</code>.
	 */
	ArrayList<String> whatTranscludesHere(String title, NS... ns);

}