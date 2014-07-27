package hempcoin.org.hempcoinwallet;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class WalletView extends Activity {

    private WebView webView;
    final Context context = this;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        webView = (WebView) findViewById(R.id.webView);
        CookieManager.getInstance().setAcceptCookie(true);
        webView.getSettings().setJavaScriptEnabled(true);
		this.webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (savedInstanceState == null)
                {
                    view.loadUrl(url);
                }
                return true;
            }
        });
        if (savedInstanceState == null)
        {
            webView.loadUrl("http://54.186.168.133/");
        }
    }
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            switch(keyCode)
            {
            case KeyEvent.KEYCODE_BACK:
                if(webView.canGoBack()){
                    webView.goBack();
                }else{
                    finish();
                }
                return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState )
    {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        webView.restoreState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.wallet_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_about) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setTitle("About the Hempcoin Wallet");
            builder1.setMessage("The (Simple) Hempcoin Wallet     - v0.0.2\n" +
                    "\n" +
                    "This application is simply a web-wrapper for the online Hempcoin wallet. We have created this application to make access much faster and simpler.\n" +
                    "\n" +
                    "This application is meant to hold users over until a more robust application can be built.\n" +
                    "\n" +
                    "For help, contact dev@hempcoin.org");
            builder1.setCancelable(true);
            builder1.setNeutralButton(android.R.string.ok,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            builder1.setIcon(R.drawable.ic_launcher);
            AlertDialog alert11 = builder1.create();
            alert11.show();
            return true;
        } else if (id == R.id.action_settings) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setTitle("Settings");
            builder1.setMessage("Coming soon, including:" +
                    "\n" +
                    " * Improved WebView usage." +
                    "\n" +
                    " * Secure and proper credentials management." +
                    "\n" +
                    "& more.\n" +
                    "\n" +
                    "For help, contact dev@hempcoin.org");
            builder1.setCancelable(true);
            builder1.setNeutralButton(android.R.string.ok,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    }
            );

            //builder1.setIcon(R.drawable.ic_launcher);
            AlertDialog alert11 = builder1.create();
            alert11.show();
            return true;
        } else if (id == R.id.action_donate) {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("donate", "HC9AwhcakbKNtmYiaqpELHVWvTnEHJ66tp");
            clipboard.setPrimaryClip(clip);

            Context context = getApplicationContext();
            CharSequence text = "Copied donation address to clipboard.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return true;
        } else if (id == R.id.action_faucet) {
            webView.loadUrl("http://sillypothead.com/thcfaucet/");
            Context context = getApplicationContext();
            CharSequence text = "Taking you to SillyPothead's faucet...";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
