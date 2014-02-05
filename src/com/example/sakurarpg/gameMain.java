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

public class gameMain extends Thread {
	// システム全体の変数
	private SurfaceHolder		mHolder;							// サーフェイスホルダー

	// スレッド関係
	private static final int	CYCLE_TIME	= 50;					// サイクルタイム=50ms

	// 画像関係
	private Bitmap				imgVdGame;							// ���z��ʂ�r�b�g�}�b�v�������C���[�W(Game)

	// 画面関係
	private static final int	VD_WIDTH	= 480;					// ���z��ʁi���j
	private static final int	VD_HEIGHT	= 854;					// ���z��ʁi�����j

	// 各種フラグ
	private boolean				bRunning	= false;				// ���C�����[�v����t���O�i�O���A�N�Z�X�j

	testOtherObject hoge;

	/*********************** 外部から呼ばれるメソッド ***********************/
	// コンストラクタ
	public gameMain(SurfaceHolder surfaceHolder, Context context) {
		this.mHolder = surfaceHolder;

		// リソースのインスタンスを取得
		Resources resources = context.getResources();

		// 各画像をインスタンス化
		imgVdGame = Bitmap.createBitmap(VD_WIDTH, VD_HEIGHT, Bitmap.Config.ARGB_8888);

		hoge = new testOtherObject(resources);

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
		canvas.drawColor(Color.argb(255, 0, 0, 32));

	}

// ***** 画面関係の処理 *****
	// bmpを画面に貼り付け
	private void doDraw(Canvas canvas) {
		/// Paintをインスタンス化
		Paint paint=new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.argb(255, 255, 255, 255));

		// 背景を塗りつぶし
		canvas.drawColor(Color.argb(255, 0, 0, 32));

		// 生成したbmp（仮想画面）を画面に表示
		canvas.drawBitmap(imgVdGame, 0, 0, paint);
	}

// ***** メイン処理 *****
	private void main() {
		/// 敵情報更新
//		moveEnemy();

		// 仮想画面生成
		genVirtualDisplay();
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

				// 画面を表示
				doDraw(canvas);

				hoge.doDraw(canvas);
				{	// for Debug
					Paint paint=new Paint();
					paint.setAntiAlias(true);

					paint.setTextSize(36);
					paint.setColor(Color.YELLOW);
					int j=2;
					canvas.drawText("loop_time="+loop_time, 0, 40*j, paint); j++;
					canvas.drawText("sleep_time="+sleep_time, 0, 40*j, paint); j++;
				}

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
		}
	}
	//タッチ入力処理
	public boolean onTouchEvent(MotionEvent me) {
		return true;
	}
}
