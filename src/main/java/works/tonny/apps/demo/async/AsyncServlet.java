/**
 * 
 */
package works.tonny.apps.demo.async;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.RandomUtils;

/**
 * @author 祥栋
 */
@WebServlet(urlPatterns = "/servlet/AsyncServlet", asyncSupported = true)
public class AsyncServlet extends HttpServlet {

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		final PrintWriter out = response.getWriter();
		final long start = System.currentTimeMillis();
		final AsyncContext asyncContext = request.startAsync(request, response);
		asyncContext.setTimeout(10 * 1000);
		final Sender run = new Sender(asyncContext, out, start);
		MyListener listener = new MyListener(run);
		asyncContext.addListener(listener);
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1L * RandomUtils.nextInt(20000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				run.n();
			}
		});
		thread.start();
		asyncContext.start(run);
		out.flush();
	}
}

class Sender implements Runnable {
	String message;
	PrintWriter out;
	long start;
	AsyncContext asyncContext;
	boolean abort = false;

	/**
	 * @param asyncContext
	 * @param out
	 * @param start
	 */
	public Sender(AsyncContext asyncContext, PrintWriter out, long start) {
		this.asyncContext = asyncContext;
		this.out = out;
		this.start = start;
	}

	public synchronized void n() {
		String[] m = new String[] { "sdfdf", "fsdfdsf", "xdf", "ddfwe", "ghj", "ghjhgj", "ghjy", "yuuyiui", "ghg",
				"tuy", "bnjgh", "tyuty", "dfdsf", "werw", "ertr", "cvcdf", "rter", "cf", "dftre", "cfgf", "retert",
				"vcgg", "ftrt" };
		message = m[RandomUtils.nextInt(m.length)];
		System.out.println("准备返回");
		notify();
	}

	public void run() {
		try {
			synchronized (this) {
				wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (abort) {
			return;
		}
		for (int i = 0; i < 1; i++) {
			out.println(System.currentTimeMillis() - start + " " + message);
			out.flush();
		}
		asyncContext.complete();
	}
}

class MyListener implements AsyncListener {
	Sender run;

	/**
	 * @param run
	 */
	public MyListener(Sender run) {
		this.run = run;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.AsyncListener#onComplete(javax.servlet.AsyncEvent)
	 */
	@Override
	public void onComplete(AsyncEvent event) throws IOException {
		System.out.println("complete");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.AsyncListener#onTimeout(javax.servlet.AsyncEvent)
	 */
	@Override
	public void onTimeout(AsyncEvent event) throws IOException {
		run.abort = true;
		event.getSuppliedResponse().getWriter().println("timeout");
		System.out.println("onTimeout");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.AsyncListener#onError(javax.servlet.AsyncEvent)
	 */
	@Override
	public void onError(AsyncEvent event) throws IOException {
		System.out.println("onError");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.AsyncListener#onStartAsync(javax.servlet.AsyncEvent)
	 */
	@Override
	public void onStartAsync(AsyncEvent event) throws IOException {
		System.out.println("onStartAsync");
	}

}
