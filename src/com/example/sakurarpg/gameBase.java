package com.example.sakurarpg;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.MotionEvent;

public class gameBase extends Thread {
	// システム全体の変数
	private SurfaceHolder		mHolder;							// サーフェイスホルダー

	// スレッド関係
	private static final int	CYCLE_TIME	= 1000/30;					// サイクルタイム=50ms

	// 画像関係
	private Bitmap				imgVdGame;							// ���z��ʂ�r�b�g�}�b�v�������C���[�W(Game)

	// 画面関係
//	private static final int	VD_WIDTH	= 480;					// ���z��ʁi���j
//	private static final int	VD_HEIGHT	= 854;					// ���z��ʁi�����j
	/** 最初にゲーム内の画面サイズを決めておく **/
	private final int VIEW_WIDTH = 1200;
	private final int VIEW_HEIGHT = 1700;
	private float _view_scale = 1;
	private float _view_w = 600;
	private float _view_h = 900;
	
	
	// 各種フラグ
	private boolean				bRunning	= false;				// ���C�����[�v����t���O�i�O���A�N�Z�X�j
	private boolean touch_flag = false;
	//testOtherObject hoge;
	gameMain _game_main;

	private float hogex;
	private float hogey;
	/*********************** 外部から呼ばれるメソッド ***********************/
	// コンストラクタ
	public gameBase(SurfaceHolder surfaceHolder, Context context, int view_w, int view_h) {
		this.mHolder = surfaceHolder;

		// リソースのインスタンスを取得
		Resources resources = context.getResources();

		// 各画像をインスタンス化
		imgVdGame = Bitmap.createBitmap(VIEW_WIDTH, VIEW_HEIGHT, Bitmap.Config.ARGB_8888);

		//hoge = new testOtherObject(resources);
		this._game_main = new gameMain(resources);

		/** SurfaceCreatedに記述 **/
		float scaleX = (float)view_w / (float)VIEW_WIDTH;
		float scaleY = (float)view_h / (float)VIEW_HEIGHT;
		this._view_w = view_w;
		this._view_h = view_h;
		this._view_scale = scaleX > scaleY ? scaleY : scaleX;
		
		this.hogex = scaleX;
		this.hogey = scaleY;

		
	}

	// メインループの動作許可設定
	public void enableRunning(boolean flag) {
		this.bRunning = flag;		// ���C�����[�v���싖��
	}

	/*********************** 内部処理 ***********************/

// ***** 画面関係の処理 *****
	// 仮想画面（ビットマップ）を生成する
	private void genVirtualDisplay() {
		// 仮想画面の下地を生成
		Canvas canvas = new Canvas(imgVdGame);

		// Paintをインスタンス化
		Paint paint =new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.argb(255, 255, 255, 255));

		// 背景を塗りつぶし
		canvas.drawColor(Color.argb(255, 0, 0, 0));

	}

// ***** 画面関係の処理 *****
	// bmpを画面に貼り付け
	// 画面の残像消しに使う
	private void doDraw(Canvas canvas) {
		/// Paintをインスタンス化
		Paint paint=new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.argb(255, 255, 255, 255));

		// 背景を塗りつぶし
		canvas.drawColor(Color.argb(255, 0, 0, 0));

		// 生成したbmp（仮想画面）を画面に表示
		canvas.drawBitmap(imgVdGame, 0, 0, paint);
	}

// ***** メイン処理 *****
	private void main() {
		/// 敵情報更新
//		moveEnemy();

		// 仮想画面生成
//		genVirtualDisplay();
	}

	/*********************** メインループ ***********************/
	// スレッドを起動すると呼ばれる
	@Override
	public void run() {
		long lastTime = System.currentTimeMillis();
		long now = 0;
		long start_time = 0;
		long loop_time = 0;
		long sleep_time = 0;
		while(bRunning) {
			// ループ開始時刻を記録
			start_time = System.currentTimeMillis();

			// メイン処理
			main();

			Canvas canvas = null;
			try {
				// 描画を開始を宣言
				canvas = mHolder.lockCanvas(null);

				/** 描画部分で記述 **/
				
				//canvas.translate((this._view_w - VIEW_WIDTH)/2*this._view_scale, (this._view_h - VIEW_HEIGHT)/2*this._view_scale); // 画面の中央になるように移動させる
				canvas.scale(this._view_scale, this._view_scale); // 端末の画面に合わせて拡大・縮小する

				// 画面を表示
				this.doDraw(canvas);

				this._game_main.autoUpdate();
				this._game_main.doDraw(canvas);
				
				Paint paint = new Paint();
				paint.setTextSize(36);
				paint.setColor(Color.YELLOW);
				int j=2;
				canvas.drawText("roop_time="+loop_time, 0, 0+40*j, paint); j++;
				canvas.drawText("fps="+(1000 / loop_time), 0, 0+40*j, paint); j++;
				canvas.drawText("sleep_time="+sleep_time, 0, 0+40*j, paint); j++;
				
				

			} catch(Exception e) {
			} finally {
				if (null != canvas) {
					//描画を終了
					mHolder.unlockCanvasAndPost(canvas);
				}
			}

			// ループが一定時間の間隔で回るための処理
			now = System.currentTimeMillis();
			loop_time = now - lastTime;		// ループ１周分の時間 for Debug
			sleep_time = CYCLE_TIME - (now - start_time);
			lastTime = now;

			
			//スリープ
			try {
				Thread.sleep(sleep_time);
			} catch (Exception e) {
			}
			///mHolder.unlockCanvasAndPost(canvas);
			
		}
	}
	public boolean touchEvent(MotionEvent me) {
		this._game_main.manualUpdate(me.getX()/this._view_scale, me.getY()/this._view_scale);
		this.touch_flag = true;
		return true;
	}
}
